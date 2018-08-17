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

const assert = require('chai').assert;
const swagger = require(__dirname + '/../../../resources/generated/swagger/swagger.json');
const generateGraphqlSchema = require(__dirname + '/../../src/graphql/graphql.js').generateGraphqlSchema;
const schema = require(__dirname + '/schema.js', 'r').schema;

/**
 * Because it's not straightforward to be able to check that the generated schema is correct,
 * this simple test simply always contains the last "expected" generated schema and checks that
 * the code still correctly generates the same schema.
 * 
 * If valid changes to the code and/or to the Swagger specification do change the generated schema,
 * simply replace the schema.js file of this test with the latest (valid) generated schema.
 * This will enforce that we will always have to check that the newly generated schema is correct.
 */

describe('Unit tests', () => {
    describe('generateGraphqlSchema', () => {
        it('generates a valid graphQL schema', () => {   
            let generatedSchema = eval(generateGraphqlSchema(swagger));
            assert.strictEqual(schema, generatedSchema);
        });
    });
});