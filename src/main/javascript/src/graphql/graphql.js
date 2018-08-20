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

const SWAGGER_TO_GRAPHQL_TYPES = {
    string: "String",
    integer: "Int",
    boolean: "Boolean",
    number: "Int",
    object: "String"
};

let graphqlTypes = [];
let graphqlTypeNames = new Set();

function generateGraphqlSchema(swagger) {
    // Generates Query part for searchProducts
    let searchProducts = _.find(swagger.paths, (obj, path) => path === '/products/search')['get'];
    let searchProductsQuery = {
        name: 'searchProducts',
        args: [],
        response: getType(searchProducts.responses['200'].schema)
    };
    searchProducts.parameters.filter(param => param.in == 'query').forEach(param => {
        let type = getType(param);
        searchProductsQuery.args.push({
            name: param.name,
            type: type
        });   
    });

    addGraphqlType(searchProductsQuery.response, swagger.definitions[searchProductsQuery.response], swagger);

    let schemaTemplate = fs.readFileSync(__dirname + '/schema.hbs', 'utf8');
    let compiledTemplate = handlebars.compile(schemaTemplate);
    let context = {
        version: swagger.info.version,
        queries: [searchProductsQuery],
        types: graphqlTypes
    }

    return compiledTemplate(context);
}

function objectWithSortedKeys(object) {
    let result = {};
    _.forEach(Object.keys(object).sort(), function(key) {
        result[key] = object[key];
    });
    return result;
}

function addGraphqlType(name, type, swagger) {
    if (graphqlTypeNames.has(name)) {
        return;
    }

    let graphqlType = {
        name: name,
        properties: []
    };
    graphqlTypeNames.add(name);
    graphqlTypes.push(graphqlType);

    let props = objectWithSortedKeys(type.properties);
    _.forEach(props, (property, prop) => {
        let propertyType = getType(property);
        let rawType = propertyType.startsWith('[') ? propertyType.slice(1, -1) : propertyType;
        if (swagger.definitions[rawType] && !graphqlTypeNames.has(rawType)) {
            addGraphqlType(rawType, swagger.definitions[rawType], swagger);
        }
        
        let required = type.required.includes(prop);
        graphqlType.properties.push({
            name: prop,
            type: propertyType,
            required: required,
            description: property.description
        });
    });
}

function getType(object) {
    var type = object.type;
    if (!type && object['$ref']) {
        var parts = object['$ref'].split('/');
        type = parts[parts.length - 1];
    } else if (type == 'array' && object.items) {
        return '[' + getType(object.items) + ']';
    } else {
        type = SWAGGER_TO_GRAPHQL_TYPES[type];
    }
    return type;
}

module.exports.generateGraphqlSchema = generateGraphqlSchema;