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

const buildSchema = require('graphql').buildSchema;
const parse = require('graphql/language').parse;
const validate = require('graphql/validation').validate;

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

        let generatedSchema = eval(generateGraphqlSchema(swagger));

        it('generates a valid graphQL schema that supports CIF graphQL products queries', () => {   
            let query = `
                query {
                    searchProducts(text: "whatever") {
                        offset
                        count
                        total
                        results {
                            id
                            sku
                            name
                            description
                            categories {
                                id
                            }
                            prices {
                                amount
                                currency
                            }
                            variants {
                                id
                                sku
                                attributes {
                                    id
                                    name
                                    value
                                    isVariantAxis
                                }
                                createdAt
                                lastModifiedAt
                            }
                        }
                    }
                }`;

            let queryAST = parse(query);
            let errors = validate(buildSchema(generatedSchema), queryAST);
            errors.forEach(e => {
                console.error(e.message);
            })
            assert.isTrue(errors.length == 0);
        });

        it('query fails with wrong query', () => {   
            let query = `
                query {
                    wrongMethod(text: "whatever") {
                        results
                    }
                }`;

            let queryAST = parse(query);
            let errors = validate(buildSchema(generatedSchema), queryAST);
            assert.isTrue(errors.length > 0);
        });

        it('query fails with wrong field', () => {   
            let query = `
                query {
                    searchProducts(text: "whatever") {
                        wrongField
                    }
                }`;

            let queryAST = parse(query);
            let errors = validate(buildSchema(generatedSchema), queryAST);
            assert.isTrue(errors.length > 0);
        });
    });
});