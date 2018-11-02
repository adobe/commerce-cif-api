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

'use strict';

const path = require('path');

const CI = require('./ci.js');
const ci = new CI();

let result = {};

let createSummary = function(auditResults) {
    let summary = {
        "vulnerabilities": {
            "info": 0,
            "low": 0,
            "moderate": 0,
            "high": 0,
            "critical": 0
        },
        "dependencies": 0,
        "devDependencies": 0,
        "optionalDependencies": 0,
        "totalDependencies": 0
    };

    for (let k in auditResults) {
        let metadata = auditResults[k].metadata;

        summary.vulnerabilities.info += metadata.vulnerabilities.info;
        summary.vulnerabilities.low += metadata.vulnerabilities.low;
        summary.vulnerabilities.moderate += metadata.vulnerabilities.moderate;
        summary.vulnerabilities.high += metadata.vulnerabilities.high;
        summary.vulnerabilities.critical += metadata.vulnerabilities.critical;
        summary.dependencies += metadata.dependencies;
        summary.devDependencies += metadata.devDependencies;
        summary.optionalDependencies += metadata.optionalDependencies;
        summary.totalDependencies += metadata.totalDependencies;
    }

    return summary;
}

// Collect audits for all packages
let projects = ci.findPackages();
let root = process.cwd();
projects.forEach((project) => {
    ci.dir(project, () => {
        let audit = ci.npmAudit();
        let relativePath = path.relative(root, project) || ".";
        result[relativePath] = audit;
    });
});

result['summary'] = createSummary(result);

ci.writeFile('audit.json', JSON.stringify(result, null, 4));