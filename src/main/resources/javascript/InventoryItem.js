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
class InventoryItem {

    /**
     * Represents a InventoryItem
     * @constructor 
     * @param {string} inventoryId
     */
    constructor(inventoryId) {
        /**
         * The inventory identifier.
         * @type {string}
         */
        this.inventoryId = inventoryId;

        /**
         * The product identifier.
         * @type {string}
         */
        this.productId = undefined;

        /**
         * The scope for the inventory (i.e store or channel).
         * @type {string}
         */
        this.scope = undefined;

        /**
         * The product available quantity for this inventory.
         * @type {integer}
         */
        this.availableQuantity = undefined;

        /**
         * The period in days when this inventory is restocked.
         * @type {integer}
         */
        this.restockDaysPeriod = undefined;

        /**
         * The next expected delivery date for this inventory.
         * @type {string}
         */
        this.expectedDeliveryDate = undefined;

    }
}
module.exports.InventoryItem = InventoryItem
