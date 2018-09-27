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
const handlebars = require('handlebars');

function generateModelClass(swagger, definition, classname) {
    let clazz = createClass(definition, classname, swagger);

    let modelTemplate = fs.readFileSync(__dirname + '/model.hbs', 'utf8');
    let compiledTemplate = handlebars.compile(modelTemplate, {noEscape: true});
    
    clazz.version = swagger.info.version;
    return compiledTemplate(clazz);
}

function createClass(definition, classname, swagger) {
    console.log('Creating class for ' + classname);

    let clazz = {
        class: classname,
        fields: []
    };

    let def = definition.allOf ? definition.allOf[1] : definition;
    clazz.imports = addImports(classname, def.properties, swagger);

    /* Support inheritance by making a class extend another one */
    if (definition.allOf) {
        clazz.parentClass = getParentClass(definition.allOf[0]);
    }

    addProperties(definition.properties, definition.required, clazz);

    return clazz;
}

function objectWithSortedKeys(object) {
    let result = {};
    _.forEach(Object.keys(object).sort(), function(key) {
        result[key] = object[key];
    });
    return result;
}

function addProperties(properties, requiredProperties, clazz) {
    let props = objectWithSortedKeys(properties);
    _.forEach(props, function(property, name) {

        let type = getType(property);
        if (type == 'array' && property.items) {
            type = getType(property.items) + '[]';
        }

        let field = {
            name: name,
            nameUpperCase: _.upperFirst(name),
            description: property.description,
            required: requiredProperties ? requiredProperties.includes(name) : false,
            type: `{${type}}`
        }

        clazz.fields.push(field);
    });
}

function addImports(classname, properties, swagger) {
    let types = new Set();
    _.forEach(properties, function(property, name) {
        let type = getType(property);
        if (type == 'array' && property.items) {
            type = getType(property.items);
        }
        if (type != classname && swagger.definitions[type]) {
            types.add(type);
        }
    });
    if (types.size) {
        return Array.from(types);
    }
}

function getParentClass(allOf) {
    let parts = allOf['$ref'].split('/');
    return parts[parts.length - 1];
}

function getType(object) {
    let type = object.type;
    if (!type && object['$ref']) {
        let parts = object['$ref'].split('/');
        type = parts[parts.length - 1];
    }
    return type;
}

module.exports.generateModelClass = generateModelClass;