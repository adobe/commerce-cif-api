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
 * @version 0.1.121
 */
class Cart {

    /**
     * Represents a Cart
     * @constructor 
     * @param {CartEntry[]} cartEntries
     * @param {string} id
     */
    constructor(cartEntries, id) {
        /**
         * The id for the cart.
         * @type {string}
         */
        this.id = id;

        /**
         * The list of the entries in the cart.
         * @type {CartEntry[]}
         */
        this.cartEntries = cartEntries;

        /**
         * If set, this defines the customer owning this cart. If not set, the cart is an anonymous cart.
         * @type {string}
         */
        this.customerId = undefined;

        /**
         * The net total price for the cart, including discounts, and shipping, but excluding any taxes.
         * @type {Price}
         */
        this.netTotalPrice = undefined;

        /**
         * The gross total price for the cart, including discounts, shipping, and all taxes.
         * @type {Price}
         */
        this.grossTotalPrice = undefined;

        /**
         * The product subtotal for the cart, including discounts and with or without taxes depending if the product prices include taxes or not.Until a shipping address is set, this field is typically used as the temporary cart total until it is known if prices include taxes or not.
         * @type {Price}
         */
        this.totalProductPrice = undefined;

        /**
         * The cart tax info, including cart entries tax and shipping info tax.
         * @type {TaxInfo}
         */
        this.cartTaxInfo = undefined;

        /**
         * Indicates if taxes are included or not in all the prices.
         * @type {boolean}
         */
        this.taxIncludedInPrices = undefined;

        /**
         * The date when this cart was created.
         * @type {string}
         */
        this.createdDate = undefined;

        /**
         * The date when this cart was last modified.
         * @type {string}
         */
        this.lastModifiedDate = undefined;

        /**
         * The shipping address for the cart products.
         * @type {Address}
         */
        this.shippingAddress = undefined;

        /**
         * The shipping info for the cart.
         * @type {ShippingInfo}
         */
        this.shippingInfo = undefined;

        /**
         * A list of all applied discounts.
         * @type {Discount[]}
         */
        this.discounts = undefined;

        /**
         * The billing address for the cart.
         * @type {Address}
         */
        this.billingAddress = undefined;

        /**
         * The payment details for the cart.
         * @type {Payment}
         */
        this.payment = undefined;

        /**
         * The currency for the cart.
         * @type {string}
         */
        this.currency = undefined;

        /**
         * A list of all coupons of the cart.
         * @type {Coupon[]}
         */
        this.coupons = undefined;

    }
}
module.exports.Cart = Cart
