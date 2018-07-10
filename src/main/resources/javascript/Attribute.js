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

/**
 * Auto generated code based on Swagger definition.
 * Dot not edit manually. Manual changes will be overridden.
 *
 * @version 0.1.120
 */
class Attribute {

    /**
     * Represents a Attribute
     * @constructor 
     * @param {string} id
     * @param {object} name
     * @param {object} value
     */
    constructor(id, name, value) {
        /**
         * The unique id for this text attribute, for example 'width'.
         * @type {string}
         */
        this.id = id;

        /**
         * The localized name for this text attribute, for example 'Width' or 'Breite'.
         * @type {object}
         */
        this.name = name;

        /**
         * The value of the attribute. This can be any arbitrary valid JSON value.
         * @type {object}
         */
        this.value = value;

        /**
         * If true, this attribute is a variant attribute. If not set or false, the attribute is a normal/simple attribute.
         * @type {boolean}
         */
        this.variantAttribute = undefined;

    }
}
module.exports.Attribute = Attribute
