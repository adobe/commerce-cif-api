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

const genFolder = __dirname + '/../../../resources/generated/graphql';
const fs = require('fs');
const fsx = require('fs-extra');
const swagger = require(__dirname + '/../../../resources/generated/swagger/swagger.json');
const generateGraphqlSchema = require(__dirname + '/graphql.js').generateGraphqlSchema;

if (!fs.existsSync(genFolder)) {
    fs.mkdirSync(genFolder);
}

let schema = fs.openSync(genFolder + '/schema.js', 'w');
fs.appendFileSync(schema, generateGraphqlSchema(swagger));

let graphqlSchema = fs.openSync(genFolder + '/schema.graphql', 'w');
let jsonSchema = require(genFolder + '/schema.js');
fs.appendFileSync(graphqlSchema, jsonSchema.schema);

// Add graphQL schema to the commerce-cif-model package so it is included in the release
fsx.copySync(genFolder + '/schema.js', __dirname + '/../../../resources/generated/javascript/graphqlSchema.js');
let index = fs.openSync(__dirname + '/../../../resources/generated/javascript/index.js', 'a');
fs.appendFileSync(index, "module.exports.graphqlSchema = require('./graphqlSchema.js').schema;\n");