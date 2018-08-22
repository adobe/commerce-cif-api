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

const genFolder = __dirname + '/../../../resources/generated/nginx';
const _ = require('lodash');
const fs = require('fs');
const escape = require('js-string-escape');
const swagger = require(__dirname + '/../../../resources/generated/swagger/swagger.json');
const generateNginxConfig = require(__dirname + '/nginx.js').generateNginxConfig;

if (!fs.existsSync(genFolder)) {
    fs.mkdirSync(genFolder);
}

let schema = fs.openSync(genFolder + '/nginx-config.txt', 'w');
fs.appendFileSync(schema, generateNginxConfig(swagger));