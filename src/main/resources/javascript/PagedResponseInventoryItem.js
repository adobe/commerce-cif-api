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
class PagedResponseInventoryItem {

    /**
     * Represents a PagedResponseInventoryItem
     * @constructor 
     */
    constructor() {
        /**
         * The offset for this response, this is the number of elements skipped, not a page number.
         * @type {integer}
         */
        this.offset = undefined;

        /**
         * The actual number of results returned in results.
         * @type {integer}
         */
        this.count = undefined;

        /**
         * The total number of results matching the query.
         * @type {integer}
         */
        this.total = undefined;

        /**
         * The results for this response.
         * @type {InventoryItem[]}
         */
        this.results = undefined;

        /**
         * The list of facets for this response.
         * @type {Facet[]}
         */
        this.facets = undefined;

    }
}
module.exports.PagedResponseInventoryItem = PagedResponseInventoryItem
