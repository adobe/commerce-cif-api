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
class ShoppingList {

    /**
     * Represents a ShoppingList
     * @constructor 
     * @param {ShoppingListEntry[]} entries
     * @param {string} id
     * @param {string} name
     */
    constructor(entries, id, name) {
        /**
         * The id of the shopping list.
         * @type {string}
         */
        this.id = id;

        /**
         * The name of the shopping list.
         * @type {string}
         */
        this.name = name;

        /**
         * The description of the shopping list.
         * @type {string}
         */
        this.description = undefined;

        /**
         * The entries of the shopping list.
         * @type {ShoppingListEntry[]}
         */
        this.entries = entries;

        /**
         * The customer id that owns this shopping list.
         * @type {string}
         */
        this.customerId = undefined;

        /**
         * The date when this shopping list was created.
         * @type {string}
         */
        this.createdDate = undefined;

        /**
         * The date when this shopping list was last modified.
         * @type {string}
         */
        this.lastModifiedDate = undefined;

    }
}
module.exports.ShoppingList = ShoppingList
