# Adobe CIF REST API

## REST

The Adobe CIF REST API follows the [REST](https://en.wikipedia.org/wiki/Representational_state_transfer) principles which involve separating the APIs into logical resources that are manipulated using HTTP requests where the method (GET, POST, PUT, PATCH, DELETE) has specific meaning. As a consequence, the API can be consumed by all standard HTTP clients.

## Authentication

Starting with version `1.3.0` of the CIF REST API, the specification introduces the `/customers/auth` endpoint which should be used to perform user authentication. This new endpoint deprecates the previous `/customers/login` endpoint.

Note: the Adobe I/O Runtime platform (based on Openwhisk) "transparently" processes HTTP POST body data in a similar manner whether it contains `application/json` or `application/x-www-form-urlencoded` data. This means that the authentication flow described below works in a similar manner if credentials are sent in JSON or via an HTML form.

### Customer authentication

To perform customer authentication, simply send an HTTP POST request to `/customers/auth` with a POST JSON body (or an equivalent HTTP `application/x-www-form-urlencoded` form) like:
```
{
  type: 'credentials',
  email: 'john.doe@example.com',
  password: 'my-secret'
}
```
A successful request will return a JSON `AuthenticationResponse` object like for example:
```
{
  access_token: '8fbnhzdotg80ua8u2ttu5g10jw58xrvc',
  token_type: 'bearer',
  expires_in: 3600,
  scope: 'optional',
  refresh_token: 'optional'
}
```
Note that the `expires_in`, `scope` and `refresh_token` fields are optional, and may be set if they are supported by the commerce backend platform.

Once an authentication token is obtained, simply add it to the subsequent HTTP request in an HTTP Authorization header, like for example
```
Authorization: Bearer 8fbnhzdotg80ua8u2ttu5g10jw58xrvc
```

### Guest authentication

Although some commerce platforms do not require that, guest (or anonymous) sessions should also be authenticated when interacting with CIF REST. This ensures that a generic CIF REST client will work with whatever commerce backend platform and whether or not it requires guest authentication.

To perform guest authentication, simply send an HTTP POST request to `/customers/auth` with a POST JSON body (or an equivalent HTTP `application/x-www-form-urlencoded` form) like:
```
{
  type: 'guest'
}
```
If the commerce backend platform supports guest authentication, this request will return a JSON `AuthenticationResponse` object similar to customer authentication. That token must then be added to subsequent HTTP requests in an `Authorization: Bearer` header.

If guest authentication is **not** supported, the response MUST be an HTTP 501 "Not implemented" response. In this case, a CIF REST client MUST consider that guest authentication is not supported and should simply continue to interact with CIF REST endpoints without sending any HTTP `Authorization` header.

If a customer login occurs after guest authentication, the CIF REST client must perform a customer authentication as described above and, if successful, must replace the guest token with the newly obtained customer token.
 
## JSON

All response payloads are in JSON format.

## Content negociation

Although the API will use only JSON for data serialization, it is good practice for clients to specify `Accept: application/json` in their request header.

## Error codes

The API uses standard HTTP response codes as error codes. For details about the error codes returned by specific endpoints you can check the [Swagger documentation](http://opensource.adobe.com/commerce-cif-api/).

## Paging and sorting

The API methods that list entities accept parameters that control sorting and pagination. You can find more details in the [Swagger documentation](http://opensource.adobe.com/commerce-cif-api/).
