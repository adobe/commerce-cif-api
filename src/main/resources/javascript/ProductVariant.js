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
class ProductVariant {

    /**
     * Represents a ProductVariant
     * @constructor 
     * @param {string} id
     */
    constructor(id) {
        /**
         * The internal unique ID of the product in the commerce backend system.
         * @type {string}
         */
        this.id = id;

        /**
         * The unique SKU of the product assigned by the vendor or manufacturer.
         * @type {string}
         */
        this.sku = undefined;

        /**
         * The name of the product.
         * @type {string}
         */
        this.name = undefined;

        /**
         * The description of the product.
         * @type {string}
         */
        this.description = undefined;

        /**
         * The prices for this product.
         * @type {Price[]}
         */
        this.prices = undefined;

        /**
         * The categories for this product.
         * @type {Category[]}
         */
        this.categories = undefined;

        /**
         * The assets for this product.
         * @type {Asset[]}
         */
        this.assets = undefined;

        /**
         * The attributes for this product.
         * @type {Attribute[]}
         */
        this.attributes = undefined;

        /**
         * The date when this product was created.
         * @type {string}
         */
        this.createdDate = undefined;

        /**
         * The date when this product was last modified.
         * @type {string}
         */
        this.lastModifiedDate = undefined;

        /**
         * Indicates if the product is available or not in the inventory.
         * @type {boolean}
         */
        this.available = undefined;

    }
}
module.exports.ProductVariant = ProductVariant
