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
const escape = require('js-string-escape');
const fs = require('fs');
const handlebars = require('handlebars');

let locations = [];

function generateNginxConfig(swagger) {
    _.forEach(swagger.paths, (endpoints, path) => {
        console.log('Creating nginx location config for ' + escape(path));
        appendNginxConfig(endpoints, escape(path), swagger);
    });

    let schemaTemplate = fs.readFileSync(__dirname + '/config.hbs', 'utf8');
    let compiledTemplate = handlebars.compile(schemaTemplate, {noEscape: true});
    let context = {
        version: swagger.info.version,
        locations: locations
    };

    return compiledTemplate(context);
}

function appendNginxConfig(endpoints, path, swagger) {
    // REPLACE with the path prefix, if any, that will be added to all the nginx 'location' directives
    // The prefix MUST start with a slash and MUST NOT end with a slash. For example: /commerce
    let location = '';

    let pathVariables = [];
    let parts = path.split('/');
    parts.filter(p => p).forEach(part => {
        if (part.startsWith('{') && part.endsWith('}')) {
            let pathVariable = part.slice(1, -1);
            pathVariables.push(pathVariable);
            location += '/(?<' + pathVariable + '>[^\/]+)';
        } else {
            location += '/' + part;
        }
    });
    
    let methods = new Map();
    _.forEach(endpoints, (endpoint, method) => {
        methods.set(method, endpoint.operationId);
    });
    
    // See http://nginx.org/en/docs/http/ngx_http_core_module.html#location
    // --> we use a prefix location with exact match when there isn't any path parameter to optimise the location matching
    if (pathVariables.length == 0) {
        location = '= ' + location;
    } else {
        location = '~ ^' + location + '$';
    }
    
    let rejectedMethods;
    let actions = [];
    if (methods.size == 1) {
        let method = methods.entries().next().value;
        let action = '/api/v1/web/' + createActionUrl(method[1], pathVariables);
        rejectedMethods = '!= ' + method[0].toUpperCase();
        actions.push({
            url: action
        });
    } else {
        let validMethods = Array.from(methods.keys()).map(m => m.toUpperCase());
        rejectedMethods = '!~ (' + validMethods.join('|') + ')';
        methods.forEach((value, key) => {
            let action = '/api/v1/web/' + createActionUrl(value, pathVariables);
            let httpMethod = key.toUpperCase();
            actions.push({
                url: action,
                method: httpMethod
            });
        });
    }
    
    locations.push({
        path: location,
        rejectedMethods: rejectedMethods,
        actions: actions
    });
}

function createActionUrl(action, pathVariables) {
    let url = '$ow_namespace/' + action + '.http';
    pathVariables.forEach((pathVariable, index) => {
        url += (index == 0 ? '?' : '&') + pathVariable + '=${' + pathVariable + '}';
    });
    return url;
}

module.exports.generateNginxConfig = generateNginxConfig;