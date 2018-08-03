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

const _ = require('lodash');
const fs = require('fs');
const fsx = require('fs-extra');
const escape = require('js-string-escape');
const swagger = require('../resources/generated/swagger/swagger.json');
const license = fs.readFileSync('license.txt');

if (!fs.existsSync('../resources/generated/javascript')) {
    fs.mkdirSync('../resources/generated/javascript');
}

var modelsPackageJson = fs.readFileSync('models-package.json', 'utf-8').replace('VERSION_PLACEHOLDER', escape(swagger.info.version));
var packageJsonFile = fs.openSync('../resources/generated/javascript/package.json', 'w');
fs.appendFileSync(packageJsonFile, modelsPackageJson);

var index = fs.openSync('../resources/generated/javascript/index.js', 'w');
fs.appendFileSync(index, license + '\n\n');

function addToIndex(classname) {
    fs.appendFileSync(index, 'module.exports.' + classname + ' = require(\'./' + classname + '.js\').' + classname + ';\n');
}

function getParentClass(allOf) {
    var parts = allOf['$ref'].split('/');
    return parts[parts.length - 1];
}

function getType(object) {
    var type = object.type;
    if (!type && object['$ref']) {
        var parts = object['$ref'].split('/');
        type = parts[parts.length - 1];
    }
    return type;
}

function addBuilderMethod(requiredProperties, file, classname) {
    _.forEach(requiredProperties, function(name) {
        fs.appendFileSync(file, `            with${_.upperFirst(name)}(${name}) {\n`);
        fs.appendFileSync(file, `                this.${name} = ${name};\n`);
        fs.appendFileSync(file, `                return this;\n`);
        fs.appendFileSync(file, `            }\n\n`);
    });
    fs.appendFileSync(file, `            build() {\n`);
    fs.appendFileSync(file, `                return new ${classname}(this);\n`);
    fs.appendFileSync(file, `            }\n`);
}

function addBuilder(definition, classname, file) {
    fs.appendFileSync(file, '\n');
    fs.appendFileSync(file, '    /**\n');
    fs.appendFileSync(file, `     * Builds a ${classname} based on API required properties.\n`);
    fs.appendFileSync(file, '     */\n');
    fs.appendFileSync(file, '    static get Builder() {\n');
    fs.appendFileSync(file, '        class Builder {\n');
    addBuilderMethod(definition.required, file, classname);
    fs.appendFileSync(file, '        }\n');
    fs.appendFileSync(file, '        return Builder;\n');
    fs.appendFileSync(file, '    }\n');
}

function addProperties(properties, requiredProperties, file) {
    _.forEach(properties, function(property, name) {

        var type = getType(property);

        if (type == 'array' && property.items) {
            type = getType(property.items) + '[]';
        }

        fs.appendFileSync(file, '        /**\n');
        fs.appendFileSync(file, '         * ' + property.description + '\n');
        fs.appendFileSync(file, '         * @type {' + type + '}\n');
        fs.appendFileSync(file, '         */\n');

        if (_.some(requiredProperties, (el) => _.includes(name, el))) {
            fs.appendFileSync(file, '        this.' + name + ' = builder.' + name + ';\n\n');
        } else {
            fs.appendFileSync(file, '        this.' + name + ' = undefined;\n\n');
        }

    });
}

function addImports(classname, properties, file) {
    let types = new Set();
    _.forEach(properties, function(property, name) {
        var type = getType(property);
        if (type == 'array' && property.items) {
            type = getType(property.items);
        }
        if (type != classname && swagger.definitions[type]) {
            types.add(type);
        }
    });
    if (types.size) {
        types.forEach(type => {
            fs.appendFileSync(file, `const ${type} = require('./${type}.js').${type};\n`);
        });
        fs.appendFileSync(file, '\n');
    }
}

function addConstructor(definition, classname, file) {
    fs.appendFileSync(file, '    /**\n');
    fs.appendFileSync(file, '     * Constructs a ' + classname + ' based on its enclosed builder.\n');
    fs.appendFileSync(file, '     * @constructor \n');
    fs.appendFileSync(file, `     * @param {Builder} builder the ${classname} builder\n`);
    fs.appendFileSync(file, '     */\n');
    fs.appendFileSync(file, '    constructor(builder) {\n');
    addProperties(definition.properties, definition.required, file);
    fs.appendFileSync(file, '    }\n');
}

function createClass(definition, classname) {
    var file = fs.openSync('../resources/generated/javascript/' + classname + '.js', 'w');
    console.log('Creating class for ' + classname);
    
    fs.appendFileSync(file, license + '\n')
    fs.appendFileSync(file, '\n/**\n');
    fs.appendFileSync(file, ' * Auto generated code based on Swagger definition.\n');
    fs.appendFileSync(file, ' * Dot not edit manually. Manual changes will be overridden.\n');
    fs.appendFileSync(file, ' *\n');
    fs.appendFileSync(file, ' * @version ' + escape(swagger.info.version) + '\n');
    fs.appendFileSync(file, ' */\n\n');

    let def = definition.allOf ? definition.allOf[1] : definition;
    addImports(classname, def.properties, file);

    /* Support inheritance by making a class extend another one */
    if (definition.allOf) {
        var parentClass = getParentClass(definition.allOf[0]);
        fs.appendFileSync(file, 'class ' + classname + ' extends ' + parentClass + ' {\n\n');
        addConstructor(definition.allOf[1], classname, file);
        addBuilder(definition.allOf[1], classname, file);
    } else {
        fs.appendFileSync(file, 'class ' + classname + ' {\n\n');
        addConstructor(definition, classname, file);
        addBuilder(definition, classname, file);
    }

    fs.appendFileSync(file, '}\n');
    fs.appendFileSync(file, 'module.exports.' + classname + ' = ' + classname + ';\n');
}

// We do a final change to the Swagger spec, by adding the Openwhisk package name to all "operationId" fields
// This is required for the 'wsk api create' configuration of the REST API in Adobe I/O Runtime
function replaceOperationIdProperties() {
    let pkg = swagger.info['x-ow-package'] || 'default';

    let swagJson = fs.readFileSync('../resources/generated/swagger/swagger.json', 'utf-8');
    swagJson = swagJson.replace(/"operationId" : "/g, '"operationId" : "' + pkg + '/');
    fs.writeFileSync('../resources/generated/swagger/swagger.json', swagJson);

    let swagYaml = fs.readFileSync('../resources/generated/swagger/swagger.yaml', 'utf-8');
    swagYaml = swagYaml.replace(/operationId: "/g, 'operationId: "' + pkg + '/');
    fs.writeFileSync('../resources/generated/swagger/swagger.yaml', swagYaml);
}

// Generates all JS model classes
_.forEach(swagger.definitions, function(definition, classname) {
        addToIndex(escape(classname));
        createClass(definition, escape(classname));
    }
);

// Add OW package name to all "operationId" fields
replaceOperationIdProperties();

// If this is NOT a SNAPSHOT version, we copy the generated files to the folders included in github
if (!swagger.info.version.includes('-SNAPSHOT')) {
    fsx.removeSync('../resources/javascript');
    fsx.removeSync('../resources/swagger');
    fsx.copySync('../resources/generated/javascript', '../resources/javascript');
    fsx.copySync('./models-readme.md', '../resources/javascript/readme.md');
    fsx.copySync('../resources/generated/swagger', '../resources/swagger');
    fsx.copySync('../resources/generated/swagger/swagger.json', '../../../docs/swagger.json');
}