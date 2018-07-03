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
 * @version 0.1.118
 */
class CartEntry {

    /**
     * Represents a CartEntry
     * @constructor 
     * @param {string} id
     * @param {ProductVariant} productVariant
     * @param {integer} quantity
     */
    constructor(id, productVariant, quantity) {
        /**
         * The id for the entry.
         * @type {string}
         */
        this.id = id;

        /**
         * The quantity for the entry.
         * @type {integer}
         */
        this.quantity = quantity;

        /**
         * The ProductVariant for the entry.
         * @type {ProductVariant}
         */
        this.productVariant = productVariant;

        /**
         * The product variant item price.
         * @type {Price}
         */
        this.unitPrice = undefined;

        /**
         * A list of all applied discounts
         * @type {Discount[]}
         */
        this.discounts = undefined;

        /**
         * The calculated cart entry price. May or may not include taxes, depending on the tax policy.
         * @type {Price}
         */
        this.cartEntryPrice = undefined;

        /**
         * The cart entry price after all discounts have been applied.
         * @type {Price}
         */
        this.discountedCartEntryPrice = undefined;

        /**
         * The cart entry tax info.
         * @type {TaxInfo}
         */
        this.cartEntryTaxInfo = undefined;

        /**
         * Cart entry type.
         * @type {string}
         */
        this.type = undefined;

    }
}
module.exports.CartEntry = CartEntry
