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
class Coupon {

    /**
     * Represents a Coupon
     * @constructor 
     * @param {string} code
     * @param {string} id
     */
    constructor(code, id) {
        /**
         * The id of the coupon instance applied to a cart. If not supported by commerce engine, should be equal to code.
         * @type {string}
         */
        this.id = id;

        /**
         * The code to activate the coupon.
         * @type {string}
         */
        this.code = code;

        /**
         * The description of the coupon.
         * @type {object}
         */
        this.description = undefined;

    }
}
module.exports.Coupon = Coupon
