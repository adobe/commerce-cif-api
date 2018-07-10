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
          console.log('Error when opening/parsing POM' + err);
        throw err;
    }
 
    release(response.pomObject.project.version);
    commit();
});

function release(pomVersion) {
    
    ci.stage('RELEASE ' + gitTag + ' ' + process.env.CIRCLE_BRANCH);
    
    console.log('Checking out the current branch so we can commit and push');
    ci.sh('git checkout ${CIRCLE_BRANCH}')
    
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
    
    ci.sh('mvn -B -s ci/settings.xml clean release:prepare release:perform -DreleaseVersion=' + newVersion + ' -DdevelopmentVersion=' + nextVersion);
    
    ci.stage('RELEASE DONE');
}

function commit() {
    // The maven release:perform goal does not commit the Javascript, Swagger, and Nginx generated files
    // so we do this here after the release

    ci.stage('COMMIT - Adding/staging/deleting all changes to the generated files');

    ci.sh('git add -A src/main/resources/javascript/*');
    ci.sh('git add -A src/main/resources/swagger/*');
    ci.sh('git add -A src/main/resources/nginx/*');
    ci.sh('git add -A docs/swagger.json');
    ci.sh('git commit -m "@releng : automatically commit generated files after maven release:perform"');
    ci.sh('git push git@github.com:adobe/commerce-cif-api.git ${CIRCLE_BRANCH}');
    
    ci.stage('COMMIT DONE');
}
