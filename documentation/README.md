# Adobe CIF cloud API

## REST

The Adobe CIF Cloud API follows the [REST](https://en.wikipedia.org/wiki/Representational_state_transfer) principles which involve separating the APIs into logical resources that are manipulated using HTTP requests where the method (GET, POST, PUT, PATCH, DELETE) has specific meaning. As a consequence, the API can be consumed by all standard HTTP clients.

## Authorization

TBD

## JSON

All response payloads are in JSON format.

## Content negociation

Although the API will use only JSON for data serialization, it is good practice for clients to specify `Accept: application/json` in their request header.

## Error codes

The API uses standard HTTP response codes as error codes. For details about the error codes returned by specific endpoints you can check the [Swagger documentation](http://opensource.adobe.com/commerce-cif-api/).

## Paging and sorting

The API methods that list entities accept parameters that control sorting and pagination. You can find more details in the [Swagger documentation](http://opensource.adobe.com/commerce-cif-api/).
