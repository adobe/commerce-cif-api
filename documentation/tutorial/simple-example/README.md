# A simple getProductById example

**Note**: in all the examples, we use the placeholders `HOST` and `NAMESPACE` to identify the Openwhisk hostname (for example, `runtime.adobe.io`) and the namespace in which you will deploy your actions. Just replace them with the right values.

## A simple static example

The file [getProductByIdStatic.js](getProductByIdStatic.js) shows an example implementation of a `getProductById` web action that returns some statically defined JSON response when called with the product id `123`. Calling this action with any other product id than `123` returns an HTTP `404 not found` response. Calling this action without an `id` parameter returns a `400 bad request` response.

To create the action in the default package of the current namespace, simply do
`wsk action create getProductById getProductByIdStatic.js --web true`

To call the action for example with `curl`, just do
`curl https://HOST/api/v1/web/NAMESPACE/default/getProductById?id=123`

Try to call the action with and without an id parameter, or with another value than `123`. If you use `curl`, you can display the HTTP response headers with the `-i` option, like in `curl -i https://HOST/api/v1/web/NAMESPACE/default/getProductById?id=123`

## Adding some callback call

Go to the [next example](../callback-example).
