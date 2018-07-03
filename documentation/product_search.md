# Product search filters & facets

The product search service is a powerful and very flexible service to retrieve product data. It can be used to query product lists based on various parameters like full-text query, attribute or price filter, products for a given category etc. It can also be used to load individual products based on some identifier like id or SKU.

## Parameters

As defined in [swagger.yaml](../src/main/resources/swagger/swagger.json) specification the product search service supports the following query parameters:

| Parameter  | Type | Description |
| ---------- | ---- | ----------- |
| text | string | Used for full-text product search. Can be used in combination with filter or facet parameters. |
| filter | string[] | Search filters to query products based on product and/or variant attributes. This paraemter is used to filter products before search facets are caluclated, like predefined filters to reduce the search scope. |
| selectedFacets | string[] | Like filter used to to query products based on product and/or variant attributes. This paraemter is used to filter products after search facets are caluclated aka. a facet was selected. |
| queryFacets | string[] | The facets list to be calculated.  |
| sort | string[] | The sort attributes and direction.  |
| offset | integer | Used for pagination, defines the number of products to skip when returning the result set.  |
| limit | integer | Defines the maximum number of products to be returned in the result set.  |

Multiple values for array parameters are sepreated by a pipe symbol, also see examples below.

The parameters work with both the public REST API as defined in Swagger spec and internal I/O Runtime endpoints as created by web actions.

## Examples

The following sample collection of search filters and facet selectors can be used as a reference. Most, not all of them, are used by the AEM connector.

### Simple queries

* Query for products with the term `shirt`:
```
.../commerce/searchProducts.http?text=shirt
```
* Query for products with the term `shirt` and sort by product name:
```
/commerce/searchProducts.http?text=shirt&sort=name.en
```
* Query for products with the term `shirt`, sort by product name and limit result set to 5:
```
/commerce/searchProducts.http?text=shirt&sort=name.en&limit=5
```

### Complex filter queries

* Filter products by category `categories.id:"<CATEGORY-ID>"`:
```
/commerce/searchProducts.http?filter=categories.id:"693b0fc5-7283-4673-a362-589d37fb7b73"
```
* Filter products by category and all its subcategories `categories.id:subtree("<CATEGORY-ID>")`:
```
/commerce/searchProducts.http?filter=categories.id:subtree("693b0fc5-7283-4673-a362-589d37fb7b73")
```
* Query products by combination of category and color attribute filter query:
```
/commerce/searchProducts?filter=categories.id:"693b0fc5-7283-4673-a362-589d37fb7b73"|variants.attributes.color.en:"red"
```
* Query products by combination of category filter and text query:
```
/commerce/searchProducts.http?filter=categories.id:subtree("693b0fc5-7283-4673-a362-589d37fb7b73")&text=gloves
```
* Query for product by sku attribute `variants.sku:"<PRODUCT-SKU>"`:
```
/commerce/searchProducts.http?filter=variants.sku:"meskwielt.1-xs"
```
* Query products by price attribute `variants.price.centAmount:<VALUE>` :
```
/commerce/searchProducts.http?filter=variants.price.centAmount:3900
```
* Query products by price range `variants.price.centAmount:range(<FROM> to <TO>)` :
```
/commerce/searchProducts.http?filter=variants.price.centAmount:range(* to 5900)
```
* Query products by category and limit result set to 5:
```
/commerce/searchProducts.http?filter=categories.id:subtree("693b0fc5-7283-4673-a362-589d37fb7b73")&limit=5
```

### Using search facets

* Calculate search facets for size and color attribute and include into response
```
/commerce/searchProducts.http?text=shirt&queryFacets=variants.attributes.size.en|variants.attributes.color.en
```
* Calculate price range search facet and include into response
```
/commerce/searchProducts.http?text=shirt&queryFacets=variants.prices.value.centAmount:range(0 to 5000),(5000 to 15000)
```
* Query for products with the term `shirt` and apply search facet filter for `size=XS` (aka. a facet was selected)
```
/commerce/searchProducts.http?text=shirt&selectedFacets=variants.attributes.size.en:"XS"&queryFacets=variants.attributes.size.en|variants.attributes.color.en
```
* Query for products with the term `shirt` and apply search facet filter for `size=XS` & `color=red` (aka. a facet was selected)
```
/commerce/searchProducts.http?text=shirt&selectedFacets=variants.attributes.size.en:"XS"|variants.attributes.color.en:"red"&queryFacets=variants.attributes.size.en|variants.attributes.color.en
```
* Query for products with the term `shirt` and apply price range filter (aka. a facet was selected)
```
/commerce/searchProducts.http?text=shirt&queryFacets=variants.prices.value.centAmount:range(0 to 5000),(5000 to 15000)&selectedFacets=variants.prices.value.centAmount:range(0 to 5000)
```
* Filter products by category `categories.id:"<CATEGORY-ID>"`, query for the term `shirt` only in this category and then apply search facet filter for `size=XS` (aka. a facet was selected). This query simulates the usage on a category page, the category is used to reduce the search scope. A user searched for `shirt` and then selected `XS` from the size facet.
```
/commerce/searchProducts.http?categories.id:"693b0fc5-7283-4673-a362-589d37fb7b73"&text=shirt&selectedFacets=variants.attributes.size.en:"XS"&queryFacets=variants.attributes.size.en
```
