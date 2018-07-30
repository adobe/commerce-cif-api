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
 * @version 0.1.124
 */
class Discount {

    /**
     * Represents a Discount
     * @constructor 
     * @param {Price} amount
     * @param {string} id
     * @param {string} type
     */
    constructor(amount, id, type) {
        /**
         * The id for the discount.
         * @type {string}
         */
        this.id = id;

        /**
         * The type of the discount.
         * @type {string}
         */
        this.type = type;

        /**
         * The name of the discount.
         * @type {string}
         */
        this.name = undefined;

        /**
         * The description associated with the discount. May be displayed in the UI.
         * @type {string}
         */
        this.description = undefined;

        /**
         * The amount which is discounted. Subtract this to obtain new price.
         * @type {Price}
         */
        this.amount = amount;

    }
}
module.exports.Discount = Discount;
