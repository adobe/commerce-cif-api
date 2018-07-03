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

function createClass(definition, classname) {
    var file = fs.openSync('../resources/generated/javascript/' + classname + '.js', 'w');
    console.log('Creating class for ' + classname);
    
    fs.appendFileSync(file, license + '\n')
    fs.appendFileSync(file, '\n/**\n');
    fs.appendFileSync(file, ' * Auto generated code based on Swagger definition.\n');
    fs.appendFileSync(file, ' * Dot not edit manually. Manual changes will be overridden.\n');
    fs.appendFileSync(file, ' *\n');
    fs.appendFileSync(file, ' * @version ' + escape(swagger.info.version) + '\n');
    fs.appendFileSync(file, ' */\n');

    /* Support inheritance by making a class extend another one */
    if (definition.allOf) {
        var parentClass = getParentClass(definition.allOf[0]);
        fs.appendFileSync(file, 'class ' + classname + ' extends ' + parentClass + ' {\n\n');
        addConstructor(definition.allOf[1], classname, file);
    } else {
        fs.appendFileSync(file, 'class ' + classname + ' {\n\n');
        addConstructor(definition, classname, file);
    }

    fs.appendFileSync(file, '}\n');
    fs.appendFileSync(file, 'module.exports.' + classname + ' = ' + classname + '\n');
}

function addConstructor(definition, classname, file) {
    fs.appendFileSync(file, '    /**\n');
    fs.appendFileSync(file, '     * Represents a ' + classname + '\n');
    fs.appendFileSync(file, '     * @constructor \n');
    _.forEach(definition.required, function(name) {
        let type = getType(definition.properties[name]);
        if (type == 'array' && definition.properties[name].items) {
            type = getType(definition.properties[name].items) + '[]';
        }
        fs.appendFileSync(file, '     * @param {' + type + '} ' +  name + '\n');
    });
    fs.appendFileSync(file, '     */\n');
    fs.appendFileSync(file, '    constructor(' + _.join(definition.required, ', ') + ') {\n');

    addProperties(definition.properties, definition.required, file);
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
            fs.appendFileSync(file, '        this.' + name + ' = ' + name + ';\n\n');
        } else {
            fs.appendFileSync(file, '        this.' + name + ' = undefined;\n\n');
        }

    });
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

// Generates all JS model classes
_.forEach(swagger.definitions, function(definition, classname) {
        addToIndex(escape(classname));
        createClass(definition, escape(classname));
    }
);

// If this is NOT a SNAPSHOT version, we copy the generated files to the folders included in github
if (!swagger.info.version.includes('-SNAPSHOT')) {
    fsx.removeSync('../resources/javascript');
    fsx.removeSync('../resources/swagger');
    fsx.copySync('../resources/generated/javascript', '../resources/javascript');
    fsx.copySync('../resources/generated/swagger', '../resources/swagger');
    fsx.copySync('../resources/generated/swagger/swagger.json', '../../../docs/swagger.json');
}