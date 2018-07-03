# Code generation
This folder contains two `nodejs` scripts that are used to
* generate the swagger specification and javascript data models/classes based on our java API and models
* generate the nginx location endpoints that we can use to confgure the OpenWhisk API gateway

## Swagger specification and javascript models (index.js)
The swagger and javascript files are generated based on the java API and models defined in `ccif-api-models/src/main/java`. To generate the latest version of the files, simply run `mvn clean compile` in the `ccif-api-models` folder.

The swagger specification files (`.json` and `.yaml`), along with a static html documentation file, are generated in the `ccif-api-models/src/main/resources/generated/swagger` folder. These generated files are git-ignored and should not be committed to the repository.

The javascript model classes are generated in the `ccif-api-models/src/main/resources/generated/javascript` folder. These generated files are git-ignored and should not be committed to the repository.

When performing a maven release of the api and models, the generated swagger and javascript files are respectively copied to the `ccif-api-models/src/main/resources/swagger` and `ccif-api-models/src/main/resources/javascript` folders. For more information about the release, see [README.md](../../../README.md).

## Nginx (nginx.js)
The current OpenWhisk default API gateway does not support path parameters. One alternative, which we might anyway require to configure the Bladerunner Adobe I/O API gateway, is to map API endpoints to web action URLs directly in the nginx configuration of either the gateway (for BR and Adobe I/O) or the nginx frontend instance for web actions (for OpenWhisk).

This is currently possible by simply running `node nginx.js` in this folder. This generates a partial nginx configuration based on the current/snapshot generated swagger specification (that is, from `ccif-api-models/src/main/resources/generated/swagger/swagger.json`). The partial nginx configuration file is generated in `ccif-api-models/src/main/resources/generated/nginx/nginx-config.txt`. This generated file is git-ignored and should not be committed to the repository. When performing a maven release of the api and models, the generated file is copied to the `ccif-api-models/src/main/resources/nginx/` folder.

This file can then be copied and integrated into the OpenWhisk nginx frontend instance. How to do that is explained in the generated `nginx-config.txt` file.

**Important**:
* the OW namespace and package names are also taken from the swagger file. These are currently defined as extra parameters in the POM file of the `ccif-api-models` project.
* the OW web action names are extracted from the `operationId` properties of all the `paths` endpoints defined in the swagger file. The `operationId` property is itself defined by either the name of a method in the corresponding API java class, or by the `nickname` annotation of the `@ApiOperation` annotation. See the current API java classes for some examples. It is of course **crucial** to use the proper action names in order for the nginx API configuration to properly work.
