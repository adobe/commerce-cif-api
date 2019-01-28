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

const genFolder = __dirname + '/../../../resources/generated';
const jsFolder = genFolder + '/javascript';
const fs = require('fs');
const fsx = require('fs-extra');
const _ = require('lodash');
const handlebars = require('handlebars');
const swagger = require(genFolder + '/swagger/swagger.json');
const generateModelClass = require(__dirname + '/model.js').generateModelClass;

if (!fs.existsSync(jsFolder)) {
    fs.mkdirSync(jsFolder);
}

// Generate package.json
let modelsPackageJson = fs.readFileSync(__dirname + '/models-package.json', 'utf-8').replace('VERSION_PLACEHOLDER', escape(swagger.info.version));
let packageJsonFile = fs.openSync(jsFolder + '/package.json', 'w');
fs.appendFileSync(packageJsonFile, modelsPackageJson);

let classes = [];
// Generates all JS model classes
_.forEach(swagger.definitions, function(definition, classname) {
    classes.push(classname);
    let model = fs.openSync(jsFolder + '/' + classname + '.js', 'w');
    fs.appendFileSync(model, generateModelClass(swagger, definition, classname));
});

// Generate index file for model package
let indexTemplate = fs.readFileSync(__dirname + '/index.hbs', 'utf8');
let compiledTemplate = handlebars.compile(indexTemplate, {noEscape: true});
let index = fs.openSync(jsFolder + '/index.js', 'w');
fs.appendFileSync(index, compiledTemplate({version: swagger.info.version, classes: classes}));

// Add OW package name to all "operationId" fields
replaceOperationIdProperties();

// We do a final change to the Swagger spec, by adding the Openwhisk package name to all "operationId" fields
// This is required for the 'wsk api create' configuration of the REST API in Adobe I/O Runtime
function replaceOperationIdProperties() {
    let pkg = swagger.info['x-ow-package'] || 'default';

    let swagJson = fs.readFileSync(genFolder + '/swagger/swagger.json', 'utf-8');
    swagJson = swagJson.replace(/"operationId" : "/g, '"operationId" : "' + pkg + '/');
    fs.writeFileSync(genFolder + '/swagger/swagger.json', swagJson);

    let swagYaml = fs.readFileSync(genFolder + '/swagger/swagger.yaml', 'utf-8');
    swagYaml = swagYaml.replace(/operationId: "/g, 'operationId: "' + pkg + '/');
    fs.writeFileSync(genFolder + '/swagger/swagger.yaml', swagYaml);
}

// We copy the Swagger spec to the commerce-cif-model package so they are included in the release
// This will make the Swagger spec available via NPM
fsx.copySync(genFolder + '/swagger/swagger.json', jsFolder + '/swagger.json');
fs.appendFileSync(index, "module.exports.swagger = require('./swagger.json');\n");