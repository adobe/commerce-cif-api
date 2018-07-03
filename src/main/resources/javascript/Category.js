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
class Category {

    /**
     * Represents a Category
     * @constructor 
     * @param {string} id
     */
    constructor(id) {
        /**
         * The internal unique ID of the category in the commerce backend system.
         * @type {string}
         */
        this.id = id;

        /**
         * The localized name of the category.
         * @type {object}
         */
        this.name = undefined;

        /**
         * The localized description of the category.
         * @type {object}
         */
        this.description = undefined;

        /**
         * The id of the main parent category (if this category has multiple parents).
         * @type {string}
         */
        this.mainParentCategoryId = undefined;

        /**
         * The list of parent categories for this category. Depending on the backend system, the returned items may only have their ids being set.
         * @type {Category[]}
         */
        this.parentCategories = undefined;

        /**
         * The list of subcategories for this category. Depending on the backend system, the returned items may only have their ids being set.
         * @type {Category[]}
         */
        this.subCategories = undefined;

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

    }
}
module.exports.Category = Category
