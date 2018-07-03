# Implementing the CIF Cloud API

This tutorial provides some basic guidance to implement the CIF Cloud API, starting with a very basic Openwhisk action all the way to implementing an example `GET /products/{id}` API endpoint.

This tutorial assumes that you either have a namespace and credentials on the Adobe I/O Runtime platform `runtime.adobe.io`, or that you have a local Openwhisk instance where you can deploy and test your actions. We also assume that you have downloaded the `wsk` CLI binary or that you have built it yourself from the Openwhisk sources, and that you have set your `wsk` credentials in your `~/.wskprops` file.

This tutorial also shows how to develop actions with Node.js as this is currently the preferred integration. We assume that the reader is familiar with (basic) javascript development and the Node.js environment.

## Action vs. web action

To implement the CIF Cloud API, you will have to write Openwhisk [web actions](https://github.com/apache/incubator-openwhisk/blob/master/docs/webactions.md). This is required because web actions return HTTP responses for which the developer has full control to set the HTTP response headers, cookies, HTTP response code, etc.

In this tutorial, we assume that you know how to write and deploy a simple [hello world action](https://github.com/apache/incubator-openwhisk/blob/master/docs/actions.md), that you understand the differences between a simple Openwhisk action and a web action, and that you also know how to write and deploy a simple web action.

## API endpoints vs. action names

The CIF Cloud API defines REST endpoints like for example `GET /products/{id}`. However in Openwhisk and Adobe I/O Runtime, a web action is actually reachable via a URL like `https://runtime.adobe.io/api/v1/web/namespace/package/action` (where `namespace` and `package` are to be replaced with your own namespace and package names).

However, users of the CIF Cloud API will eventually interact with the REST API, and the Adobe I/O Runtime gateway will basically forward incoming HTTP requests for the REST API to the corresponding web action URL, where for example a GET request to:

`GET https://runtime.adobe.io/apis/namespace/package/products/123` will be forwarded to 
`GET https://runtime.adobe.io/api/v1/web/namespace/package/getProductById?id=123`

This means that implementing a particular REST API endpoint requires that the corresponding action has a specific name, for example `getProductById` for the `GET /products/{id}` REST endpoint.

The action names for all the REST API endpoints are defined in the [CIF Cloud API Swagger documentation](../api/swagger.json), where each endpoint specifies its expected action name in the `operationId` field. It is crucial that you use the correct names for your actions.

## Input parameters and response models

The CIF Cloud API Swagger documentation defines, for each endpoint, the list of input parameters (if any) expected by the endpoint/action, and the JSON model that should be returned in the HTTP response. Make sure that each endpoint returns the expected JSON response, and that all mandatory fields are set.

The Swagger documentation also documents what response code should be returned in case of errors, for example, an HTTP response code `404` (not found) when `GET /products/{id}` is called for a product id that doesn't exist.

# A simple getProductById example

**Note**: in all the examples, we use the placeholders `HOST` and `NAMESPACE` to identify the Openwhisk hostname (for example, `runtime.adobe.io`) and the namespace in which you will deploy your actions. Just replace them with the right values.

## A simple static example

This example is described in the [simple-example](simple-example) folder.

## Adding some callback call

This example is described in the [callback-example](callback-example) folder.

## What's next?

The next step is simply to replace the call to the commerce backend with a real commerce system URL, and build a real function to map the models of the commerce backend into the CIF Cloud object models.

If you are testing your actions with the We.Retail AEM demo website, you will need to implement the following REST API endpoints/actions to get the categories and products page working:
* `GET /categories` with action name `getCategories`
* `GET /products/search` with action name `searchProducts`, implementing the following search filters:
  * `categories.id:"CATEGORY-ID"` (will return the list of products for that category)
  * `categories.id:subtree("CATEGORY-ID")` (will return the list of products for that category and all its subcategories)
  * `variants.sku:"PRODUCT-SKU"` (will return the product data for the given product's sku)
