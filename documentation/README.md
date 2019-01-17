# Adobe CIF cloud API

## REST

The Adobe CIF Cloud API follows the [REST](https://en.wikipedia.org/wiki/Representational_state_transfer) principles which involve separating the APIs into logical resources that are manipulated using HTTP requests where the method (GET, POST, PUT, PATCH, DELETE) has specific meaning. As a consequence, the API can be consumed by all standard HTTP clients.

## Authentication

Starting with version `1.3.0` of the CIF API, the specification introduces the `/customers/auth` endpoint which should be used to perform user authentication and deprecates the previous `/customers/login` endpoint.

### Customer authentication

To perform customer authentication, simply send an HTTP POST request to `/customers/auth` with a POST JSON body like:
```json
{
    type: 'credentials',
    email: 'john.doe@example.com',
    password: 'my-secret'
}
```
A successful request will return a JSON `AuthenticationResponse` object like for example:
```json
{
   access_token: '8fbnhzdotg80ua8u2ttu5g10jw58xrvc',
   token_type: 'bearer',
   expires_in: 3600,
   scope: 'optional'
   refresh_token: 'optional'
}
```
Note that the `expires_in`, `scope` and `refresh_token` fields are optional, and may be set if they are supported by the commerce backend platform.

Once an authentication token is obtained, simply add it to the subsequent HTTP request in an HTTP Authorization header, like for example
```
Authorization: Bearer 8fbnhzdotg80ua8u2ttu5g10jw58xrvc
```

### Guest authentication

Although some commerce platforms do not require that, guest (or anonymous) sessions should also be authenticated when interacting with CIF. This ensures that a client will work with whatever commerce backend platform and whether or not it requires guest authentication.

To perform guest authentication, simply send an HTTP POST request to `/customers/auth` with a POST JSON body like:
```json
{
    type: 'guest'
}
```
If the commerce backend platform supports guest authentication, this request will return a JSON `AuthenticationResponse` object similar to customer authentication. That token must then be added to subsequent HTTP requests in an `Authorization: Bearer` header.

If guest authentication is **not** supported, the response MUST be an HTTP 501 "Not implemented" response. In this case, a CIF client must consider that guest authentication is not supported and should simply continue to interact with CIF endpoints without sending any HTTP `Authorization` header.
 
## JSON

All response payloads are in JSON format.

## Content negociation

Although the API will use only JSON for data serialization, it is good practice for clients to specify `Accept: application/json` in their request header.

## Error codes

The API uses standard HTTP response codes as error codes. For details about the error codes returned by specific endpoints you can check the [Swagger documentation](http://opensource.adobe.com/commerce-cif-api/).

## Paging and sorting

The API methods that list entities accept parameters that control sorting and pagination. You can find more details in the [Swagger documentation](http://opensource.adobe.com/commerce-cif-api/).
