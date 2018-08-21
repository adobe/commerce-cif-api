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

const resourcesFolder = __dirname + '/../resources';
const genFolder = resourcesFolder + '/generated';
const fsx = require('fs-extra');
const swagger = require(genFolder + '/swagger/swagger.json');

/**
 * When buidling a released version, this script copies all the generated folders
 * to the "released" resources folders.
 */

let folders = ['javascript', 'nginx', 'swagger', 'graphql'];

if (!swagger.info.version.includes('-SNAPSHOT')) {
    folders.forEach(folder => {
        fsx.removeSync(resourcesFolder + '/' + folder);
        fsx.copySync(genFolder + '/' + folder, resourcesFolder + '/' + folder);
    });

    fsx.copySync('./models-readme.md', resourcesFolder + '/javascript/readme.md');
}