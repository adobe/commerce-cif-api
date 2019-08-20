[![CircleCI](https://circleci.com/gh/adobe/commerce-cif-api.svg?style=svg)](https://circleci.com/gh/adobe/commerce-cif-api)
[![codecov](https://codecov.io/gh/adobe/commerce-cif-api/branch/master/graph/badge.svg)](https://codecov.io/gh/adobe/commerce-cif-api)
[![Swagger](https://img.shields.io/badge/Swagger-CIF%20API-brightgreen.svg)](http://opensource.adobe.com/commerce-cif-api/)
[![Maven Central](https://img.shields.io/maven-central/v/com.adobe.commerce.cif/api-model.svg)](https://search.maven.org/search?q=g:com.adobe.commerce.cif%20AND%20a:api-model)
[![npm (scoped)](https://img.shields.io/npm/v/@adobe/commerce-cif-model.svg)](https://www.npmjs.com/package/@adobe/commerce-cif-model)

# Adobe CIF REST API and data model (NOTE: Replaced with new API / Github; CIF REST API and data model not supported by Adobe anymore)

This repository contains the API reference and data model documentation files for the Adobe Commerce Integration Framework (CIF REST) API.

## API

The CIF REST API is the heart of all programmatic interactions with Adobe's commerce integrations. The goal of the CIF REST API is to provide a comprehensive and extendable commerce storefront API. The API defines endpoints and data models suitable for all channels of customer facing shopping experiences. 

Using the CIF REST API, you can:

* Retrieve product information for a single product or a collection of products and use it on any website or device.
* Invoke a product search and filter to search results.
* Create omni channel checkout experiences with flexible control over the shopping cart and checkout flow.
* Create and login customer users to build personalized experiences.

CIF REST services are exposed via REST style APIs. All API access via Adobe I/O Runtime is over HTTPS. All CIF REST services return the information that you request as JSON data format.

The CIF REST API is not a full CRUD commerce admin or management API, for example it is not designed for data import / export use cases.

### API Specification

The API and data models are specified in [swagger.yaml](src/main/resources/swagger/swagger.yaml) / [swagger.json](src/main/resources/swagger/swagger.json). Review directly in [Swagger UI](http://opensource.adobe.com/commerce-cif-api/).

The API and models are actually defined in Java with both `swagger` and `jax-rs` annotations, so we do not directly edit the Swagger JSON and YAML specification. These Java definitions can be found under [src/main/java](src/main/java). They are used to automatically generate:
* the Swagger specification: [src/main/resources/swagger](src/main/resources/swagger)
* the Javascript model classes used by the CIF REST implementations written in `Node.js`: [src/main/resources/javascript](src/main/resources/javascript)
* the Nginx configuration that can be used to configure the CIF REST API endpoints on an Openwhisk environment: [src/main/resources/nginx](src/main/resources/nginx)

Note that the build actually generates these files under the `src/main/resources/generated` folder, but this folder is not committed on the git repository. The `maven release` process actually copies the generated files to the folders mentioned above so that we only have released files in the repository.

### Using the API or building the SNAPSHOT version

To use the API in an existing Maven project, simply add the following dependency:

```xml
<dependency>
    <groupId>com.adobe.commerce.cif</groupId>
    <artifactId>api-model</artifactId>
    <version>...</version>
</dependency>
```

For a Node.js project, just add the `@adobe/commerce-cif-model` package dependency to your project.

If you want to build and install the SNAPSHOT version, just do `mvn clean install`

The code of the Node.js package, the Swagger specification, and the Nginx configuration is generated into the `src/main/resources/generated` folder (which is not included in this git repository). The other folders under `src/main/resources` are automatically updated by the release process and should not be updated manually.

### Making changes

To make changes to the API and/or object models, simply edit or add new Java model(s) and api class(es). Running `mvn clean package` will then regenerate the Swagger specification, documentation, Javascript model classes, and Nginx configuration.

The Java unit tests in [src/test](src/test) ensure that all model classes define proper `public` getter and setter methods for all fields with `protected` modifier. This ensures that Java libraries like `jackson` or `GSON` will be able to convert JSON responses into Java objects and that these Java objects can easily be extended.

### JSON model examples

The [resources](src/test/resources) folder contains some JSON model examples that can help during the development of the CIF REST API. Check the [README](src/test/resources) in that folder for more information about the examples.

Note that these examples are also used by unit tests to ensure that they are always up-to-date with the Java models. This means that a change in a model usually requires applying that change to the corresponsing JSON example. This ensures that all examples are always also updated so that they accurately represent the JSON responses expected by the CIF REST API.

## Adobe I/O Runtime

The CIF REST services architecture is based on [Apache OpenWhisk](https://openwhisk.apache.org) & [Adobe I/O Runtime](https://www.adobe.io/apis/cloudplatform/runtime.html). The main building blocks of the new commerce services are serverless functions (OpenWhisk actions). These actions run on Adobe I/O Runtime inside an isolated container, stateless and serverless interacting with the commerce backend system or other endpoints via their APIs. 

### Configuring the REST API on Adobe I/O Runtime

The current implementations of the CIF REST API for [Magento](https://github.com/adobe/commerce-cif-magento) and [Commercetools](https://github.com/adobe/commerce-cif-commercetools) expose web actions that can be called directly and that do not automatically "implement" the CIF REST API. For example, the `/products/{id}` endpoint would be reachable via the following URL, assuming an example `demo` namespace with the actions deployed in the `magento` package, looking up a product with id `123`:

`https://runtime.adobe.io/api/v1/web/demo/magento/getProductById?id=123`

The configuration of the REST API is done "on top" of those web actions, based on the Swagger specification and deployed with the `wsk` [OpenWhisk CLI](https://github.com/apache/incubator-openwhisk-cli/releases). Because each user of the API will deploy the actions in its own namespace and in a dedicated package, one has to regenerate the Swagger specification for its own environment before deploying the REST API.

To do so, simply edit the [pom.xml](pom.xml) file of the project, and replace the following properties of the `swagger-maven-plugin`:
* `basePath`: the base path via which your REST API will be reachable, for example `/commerce`
* `x-ow-namespace`: the namespace in which your CIF REST actions are deployed
* `x-ow-package`: the package in which your CIF REST actions are deployed

then rebuild the project with `mvn clean compile`. This will regenerate the Swagger documentation in `src/main/resources/generated/swagger/`.

To now deploy the REST API, make sure your `wsk` CLI is properly configured with your Adobe I/O Runtime credentials, and run:

`wsk api create --config-file src/main/resources/generated/swagger/swagger.json`

If the command is successful, it will display all the REST API endpoints that are configured. You can execute `wsk api list /basePath` with the `basePath` you configured to list again all your REST API endpoints.

For the previous example, this would have created the following REST API endpoint for `getProductById`:

`https://runtime.adobe.io/apis/demo/commerce/products/123`

This endpoint basically fowards all requests to the corresponding web action. Note that in a later stage, one might also want to block all requests to all the web action endpoints, in order to only have the REST API reachable via Adobe I/O Runtime.

### Configuring the REST API on a local Openwhisk instance

The configuration of the REST API on a local Openwhisk instance is done differently. One has to do the same changes to the `pom.xml`, rebuild the project, and this also generates the file `src/main/resources/generated/nginx/nginx-config.txt`. See the instructions at the top of this file to add this nginx extra configuration to a local Openwhisk instance.

## Implementing the CIF REST API

If you plan to implement the CIF REST API for a particular commerce system, you can follow the [tutorial](documentation/tutorial) that guides you into developing one particular API endpoint.

### Product Search

The product search service supports a flexible approach to query & filter product data by full-text search or attributes. This [document](documentation/product_search.md) has a variety of examples.

## Online Swagger-ui documentation

To publish our CIF REST Swagger specification, a [github page](http://opensource.adobe.com/commerce-cif-api/) was 
configured for the repository.
The page is configured to display the content of the [docs](docs) folder, which is basically a slightly modified version of swagger-ui.

### Contributing
 
Contributions are welcomed! Read the [Contributing Guide](.github/CONTRIBUTING.md) for more information.
 
### Licensing
 
This project is licensed under the Apache V2 License. See [LICENSE](LICENSE) for more information. 
