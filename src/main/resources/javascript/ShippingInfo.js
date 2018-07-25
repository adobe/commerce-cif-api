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
 * @version 0.1.123
 */
class ShippingInfo {

    /**
     * Represents a ShippingInfo
     * @constructor 
     * @param {string} name
     * @param {Price} price
     */
    constructor(name, price) {
        /**
         * The shipping method name.
         * @type {string}
         */
        this.name = name;

        /**
         * The shipping price.
         * @type {Price}
         */
        this.price = price;

        /**
         * The discounted shipping price.
         * @type {Price}
         */
        this.discountedPrice = undefined;

        /**
         * The tax for the shipping.
         * @type {TaxInfo}
         */
        this.shippingTaxInfo = undefined;

        /**
         * The shipping method id.
         * @type {string}
         */
        this.id = undefined;

    }
}
module.exports.ShippingInfo = ShippingInfo
