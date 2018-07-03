# Adobe CIF Cloud model examples

This folder contains some JSON model examples for all the models returned by the CIF Cloud API endpoints.

To keep each example short, we decided to sometimes set some values to empty objects or empty arrays. This usually means that there is another example that shows how to set an object of that particular type. For example, the field `billingAddress` is set to an empty object in [cart.json](cart.json), but a complete example address can be found in [address.json](address.json).

Also note that these examples typically set all the fields of each "top-level" object defined in each JSON file, this doesn't mean that all the fields are mandatory. Please refer to the Swagger specification to check which fields are mandatory or not.
