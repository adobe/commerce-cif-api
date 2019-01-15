/*******************************************************************************
 *
 *    Copyright 2018 Adobe. All rights reserved.
 *    This file is licensed to you under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License. You may obtain a copy
 *    of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software distributed under
 *    the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
 *    OF ANY KIND, either express or implied. See the License for the specific language
 *    governing permissions and limitations under the License.
 *
 ******************************************************************************/

'use strict';

const pomParser = require('pom-parser');
const semver = require('semver');
const CI = require('./ci.js');
const ci = new CI();
const fs = require('fs');
const cp = require('child_process');

ci.context();

let gitTag = process.env.CIRCLE_TAG;
if (!gitTag) {
    throw 'Cannot release without a valid git tag';
}

var opts = {
    filePath: __dirname + '/../pom.xml',
};

pomParser.parse(opts, function(err, response) {
    if (err) {
        console.error('Error when opening/parsing POM' + err);
        throw err;
    }
 
    try {
        ci.gitImpersonate('CircleCi', 'noreply@circleci.com', () => {
            release(response.pomObject.project.version);
            updateSwaggerUiIndex();
        });
    } finally {
        // Remove release tag
        ci.sh('git push --delete origin ' + gitTag);
        
        // Remove NPM token and 'gpg' config
        ci.sh('rm -f ~/.npmrc');
        ci.sh('rm -rf /home/circleci/.gnupg');
    }
});

function release(pomVersion) {
    
    ci.stage('RELEASE ' + gitTag);
    
    // We cannot find out what git branch has the tag, so we assume/enforce that releases are done on master
    console.log('Checking out the master branch so we can commit and push');
    ci.sh('git checkout master');
    
    let newVersion, nextVersion;
    let currentVersion = semver.coerce(pomVersion).version;
    
    if (gitTag.endsWith('-patch')) {
        if (pomVersion.includes('-SNAPSHOT')) {
            newVersion = currentVersion;
        } else {
            newVersion = semver.inc(currentVersion, 'patch');
        }
    } else if (gitTag.endsWith('-minor')) {
        newVersion = semver.inc(currentVersion, 'minor');
    } else if (gitTag.endsWith('-major')) {
        newVersion = semver.inc(currentVersion, 'major');
    }
    nextVersion = semver.inc(newVersion, 'patch') + '-SNAPSHOT';
    
    console.log('Current version  : ' + pomVersion);
    console.log('Released version : ' + newVersion);
    console.log('Next version     : ' + nextVersion);
    
    // Import PGP key into 'gpg' config
    ci.sh('echo $GPG_PRIVATE_KEY | base64 --decode | gpg --batch --import');
    
    // Configure NPM token
    ci.sh('echo "//registry.npmjs.org/:_authToken=$NPM_TOKEN" >> ~/.npmrc');
    
    ci.sh('mvn -B -s ci/settings.xml clean release:prepare release:perform -DreleaseVersion=' + newVersion + ' -DdevelopmentVersion=' + nextVersion);
    
    ci.stage('RELEASE DONE');
}

/**
 * Updates the Swagger UI index.html file with the newly release Swagger specification
 */
function updateSwaggerUiIndex() {
    let tags = cp.execSync(`git tag -l api-model-*`).toString().trim().split('\n');

    // We ignore all 0.x versions and versions 1.0.0 and 1.1.0
    // Due to an issue in the first releases, versions 1.0.0 and 1.1.0 have to be handled differently
    tags = tags.filter(tag => !tag.startsWith('api-model-0.') && tag != 'api-model-1.0.0' && tag != 'api-model-1.1.0');

    let urls = [
        {url: "http://opensource.adobe.com/commerce-cif-api/swagger-1.1.0.json", name: "CIF Cloud API 1.1.0"},
        {url: "http://opensource.adobe.com/commerce-cif-api/swagger-1.0.0.json", name: "CIF Cloud API 1.0.0"}
    ];

    tags.forEach(tag => {
        urls.unshift({
            url: `https://raw.githubusercontent.com/adobe/commerce-cif-api/${tag}/src/main/resources/swagger/swagger.json`,
            name: 'CIF Cloud API ' + tag.substring(10)
        });
    });

    // replace the 'urls' array in index.html
    let index = fs.readFileSync(__dirname + '/../docs/index.html', 'utf-8');
    index = index.replace(/urls: [\s\S]*?\]/, 'urls: ' + JSON.stringify(urls, null, 4));
    fs.writeFileSync(__dirname + '/../docs/index.html', index);

    ci.stage('COMMIT - Adding Swagger UI new index file');

    ci.sh('git add -A docs/index.html');
    ci.sh('git commit -m "@releng : automatically commit Swagger UI index after maven release:perform"');
    ci.sh('git push git@github.com:adobe/commerce-cif-api.git');
    
    ci.stage('COMMIT DONE');
}