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
 * Auto generated graphQL schema based on Swagger definition.
 * Dot not edit manually. Manual changes will be overridden.
 *
 * @version 1.1.1-SNAPSHOT
 */

let schema = `
{
    type Query {
        searchProducts(text: String, filter: [String], selectedFacets: [String], queryFacets: [String], sort: [String], offset: Int, limit: Int): PagedResponseProduct
    }

    type PagedResponseProduct {
        # The offset for this response, this is the number of elements skipped, not a page number.
        offset: Int!
        # The actual number of results returned in results.
        count: Int!
        # The total number of results matching the query.
        total: Int!
        # The results for this response.
        results: [Product]!
        # The list of facets for this response.
        facets: [Facet]
    }

    type Product {
        # The date-time when this object was created. The JSON representation must be in RFC339 / ISO8601 format
        createdAt: String
        # The date-time when this object was last modified. The JSON representation must be in RFC339 / ISO8601 format
        lastModifiedAt: String
        # The internal unique ID of the product in the commerce backend system.
        id: String!
        # The name of the product.
        name: String!
        # The description of the product.
        description: String
        # The prices for this product.
        prices: [MoneyValue]!
        # The categories for this product.
        categories: [Category]
        # The assets for this product.
        assets: [Asset]
        # The attributes for this product.
        attributes: [Attribute]
        # The unique SKU of the product assigned by the vendor or manufacturer.
        sku: String
        # The id of the master variant
        masterVariantId: String!
        # The variants for this product.
        variants: [ProductVariant]!
    }

    type MoneyValue {
        # The currency code for this money value.
        currency: String!
        # The amount in cents of this money value.
        amount: Int!
        # The country code for this money value.
        country: String
    }

    type Category {
        # The date-time when this object was created. The JSON representation must be in RFC339 / ISO8601 format
        createdAt: String
        # The date-time when this object was last modified. The JSON representation must be in RFC339 / ISO8601 format
        lastModifiedAt: String
        # The internal unique ID of the category in the commerce backend system.
        id: String!
        # The name of the category.
        name: String
        # The description of the category.
        description: String
        # The id of the main parent category (if this category has multiple parents).
        mainParentId: String
        # The list of parent categories for this category. Depending on the backend system, the returned items may only have their ids being set.
        parents: [Category]
        # The list of subcategories for this category. Depending on the backend system, the returned items may only have their ids being set.
        children: [Category]
    }

    type Asset {
        # The id of the asset.
        id: String!
        # The url of the asset.
        url: String!
    }

    type Attribute {
        # The unique id for this text attribute, for example &#x27;width&#x27;.
        id: String!
        # The name for this text attribute.
        name: String!
        # The value of the attribute. This can be any arbitrary valid JSON value.
        value: String!
        # If true, this attribute is a variant attribute. If not set or false, the attribute is a normal/simple attribute.
        isVariantAxis: Boolean
    }

    type ProductVariant {
        # The date-time when this object was created. The JSON representation must be in RFC339 / ISO8601 format
        createdAt: String
        # The date-time when this object was last modified. The JSON representation must be in RFC339 / ISO8601 format
        lastModifiedAt: String
        # The internal unique ID of the product in the commerce backend system.
        id: String!
        # The name of the product.
        name: String!
        # The description of the product.
        description: String
        # The prices for this product.
        prices: [MoneyValue]!
        # The categories for this product.
        categories: [Category]
        # The assets for this product.
        assets: [Asset]
        # The attributes for this product.
        attributes: [Attribute]
        # The unique SKU of the product variant assigned by the vendor or manufacturer.
        sku: String!
        # Indicates if the product is available or not in the inventory.
        available: Boolean!
    }

    type Facet {
        # The id of the facet.
        id: String!
        # The name of the facet.
        name: String!
        # The number of missed items.
        missed: Int
        # Indicates if the facet is multi selectable.
        multiSelect: Boolean
        # The type of the facet.
        type: String!
        # List of facetValues calculated for this facet.
        values: [FacetValue]!
    }

    type FacetValue {
        # The id for this facet.
        id: String!
        # The value for this facet.
        value: String!
        # The number of facet value occurrences.
        occurrences: Int
        # Indicates if the current facet value was selected.
        selected: Boolean
    }

}`;

module.exports.schema = schema;