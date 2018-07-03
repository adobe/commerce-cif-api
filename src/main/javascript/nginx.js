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

const genFolder = __dirname + '/../resources/generated';
const _ = require('lodash');
const fs = require('fs');
const fsx = require('fs-extra');
const escape = require('js-string-escape');
const swagger = require(genFolder + '/swagger/swagger.json');

if (!fs.existsSync(genFolder + '/nginx')) {
    fs.mkdirSync(genFolder + '/nginx');
}

const config = fs.openSync(genFolder + '/nginx/nginx-config.txt', 'w');
fs.appendFileSync(config, '\n');
fs.appendFileSync(config, '# 1) Verify the 3 variables below this header and set them to match your OpenWhisk deployment.\n');
fs.appendFileSync(config, '#    For a local OW deployment, you need to uncomment the line setting the $controller variable.\n');
fs.appendFileSync(config, '# 2) Copy the content of this file in ansible/roles/nginx/templates/nginx.conf.j2\n');
fs.appendFileSync(config, '#    before the first location directive that already exists in the file\n');
fs.appendFileSync(config, '# 3) Restart the Openwhisk nginx/edge component by running "wskdev edge"\n');
fs.appendFileSync(config, '#\n');
fs.appendFileSync(config, '# CCIF API Version ' + escape(swagger.info.version) + '\n\n');

fs.appendFileSync(config, '# set $controller "http://controllers";\n');
fs.appendFileSync(config, 'set $ow_namespace "' + escape(swagger.info['x-ow-namespace'] || 'guest') + '";\n');
fs.appendFileSync(config, 'set $ow_package "' + escape(swagger.info['x-ow-package'] || 'default') + '";\n\n');

_.forEach(swagger.paths, (endpoints, path) => {
    console.log('Creating nginx location config for ' + escape(path));
    appendNginxConfig(endpoints, escape(path));
});

// If this is NOT a SNAPSHOT version, we copy the generated files to the folders included in github
if (!swagger.info.version.includes('-SNAPSHOT')) {
    fsx.removeSync(__dirname + '/../resources/nginx');
    fsx.copySync(genFolder + '/nginx', __dirname + '/../resources/nginx');
}

function appendNginxConfig(endpoints, path) {
    let pathVariables = [];
    let basePath = escape(swagger.basePath) || '';
    if (basePath) {
        basePath = '/' + _.trim(basePath, '/');
    }
    
    let location = '';
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
        location = basePath + location;
        fs.appendFileSync(config, 'location = ' + location + ' {\n');
    } else {
        location = '^' + basePath + location + '$';
        fs.appendFileSync(config, 'location ~ ' + location + ' {\n');
    }
    
    if (methods.size == 1) {
        let method = methods.entries().next().value;
        let action = createActionUrl(method[1], pathVariables);
        fs.appendFileSync(config, '  if ($request_method != ' + method[0].toUpperCase() + ') {\n');
        fs.appendFileSync(config, '    return 405;\n');
        fs.appendFileSync(config, '  }\n');
        fs.appendFileSync(config, '  rewrite ^ /api/v1/web/' + action + ' break;\n');
    } else {
        let validMethods = Array.from(methods.keys()).map(m => m.toUpperCase());
        fs.appendFileSync(config, '  if ($request_method !~ (' + validMethods.join('|') + ')) {\n');
        fs.appendFileSync(config, '    return 405;\n');
        fs.appendFileSync(config, '  }\n');
        methods.forEach((value, key) => {
            let action = createActionUrl(value, pathVariables);
            fs.appendFileSync(config, '  if ($request_method = ' + key.toUpperCase() + ') {\n');
            fs.appendFileSync(config, '    rewrite ^ /api/v1/web/' + action + ' break;\n');
            fs.appendFileSync(config, '  }\n');
        });
    }
    
    fs.appendFileSync(config, '  proxy_pass $controller;\n');
    fs.appendFileSync(config, '  proxy_read_timeout 70s;\n');
    fs.appendFileSync(config, '}\n\n');
}

function createActionUrl(action, pathVariables) {
    let url = '$ow_namespace/$ow_package/' + action + '.http';
    pathVariables.forEach((pathVariable, index) => {
        url += (index == 0 ? '?' : '&') + pathVariable + '=${' + pathVariable + '}';
    });
    return url;
}