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

package com.adobe.commerce.cif.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.adobe.commerce.cif.model.customer.AuthenticationResponse;
import com.adobe.commerce.cif.model.customer.Customer;
import com.adobe.commerce.cif.model.customer.LoginResult;
import com.adobe.commerce.cif.model.error.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.adobe.commerce.cif.api.Constants.ACCEPT_LANGUAGE;
import static com.adobe.commerce.cif.api.Constants.ACCEPT_LANGUAGE_DESC;
import static com.adobe.commerce.cif.api.Constants.AUTHORIZATION;
import static com.adobe.commerce.cif.api.Constants.AUTHORIZATION_DESC;
import static com.adobe.commerce.cif.api.Constants.HTTP_BAD_REQUEST;
import static com.adobe.commerce.cif.api.Constants.HTTP_BAD_REQUEST_MESSAGE;
import static com.adobe.commerce.cif.api.Constants.HTTP_NOT_FOUND;
import static com.adobe.commerce.cif.api.Constants.HTTP_NOT_FOUND_MESSAGE;
import static com.adobe.commerce.cif.api.Constants.HTTP_OK;
import static com.adobe.commerce.cif.api.Constants.HTTP_OK_MESSAGE;
import static com.adobe.commerce.cif.api.Constants.HTTP_UNAUTHORIZED;
import static com.adobe.commerce.cif.api.Constants.HTTP_UNAUTHORIZED_MESSAGE;

@Path("/customers")
@Api(value = "/customers")
@Produces(MediaType.APPLICATION_JSON)
public interface CustomerApi {

    @GET
    @Path("/me")
    @ApiOperation(value = "Returns customer data based on the authorization header bearer token.")
    @ApiResponses(value = {
        @ApiResponse(code = HTTP_BAD_REQUEST, message = HTTP_BAD_REQUEST_MESSAGE, response = ErrorResponse.class),
        @ApiResponse(code = HTTP_UNAUTHORIZED, message = HTTP_UNAUTHORIZED_MESSAGE, response = ErrorResponse.class)
    })
    Customer getCustomer(
        @ApiParam(value = AUTHORIZATION_DESC, required = true)
        @HeaderParam(AUTHORIZATION) String token,
        
        @ApiParam(value = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE) String acceptLanguage
    );
    
    @Deprecated
    @GET
    @Path("/{id}")
    @ApiOperation(value = "Returns a customer by ID.")
    @ApiResponses(value = {
        @ApiResponse(code = HTTP_BAD_REQUEST, message = HTTP_BAD_REQUEST_MESSAGE, response = ErrorResponse.class),
        @ApiResponse(code = HTTP_NOT_FOUND, message = HTTP_NOT_FOUND_MESSAGE, response = ErrorResponse.class)
    })
    Customer getCustomerById(
        @ApiParam(value = "The id of the customer.", required = true)
        @PathParam("id")
        String id,

        @ApiParam(value = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE) String acceptLanguage
    );

    @POST
    @Path("/auth")
    @ApiOperation(value = "Performs an authentication request to obtain a customer access token.",
        notes = "Currently two types are supported: \"guest\" returns a token for a new guest (non-authenticated) " +
            "customer and \"credentials\" authenticates a registered customer and returns a token upon successful " +
            "authentication otherwise results in an error."
    )
    @ApiResponses(value = {
        @ApiResponse(code = HTTP_OK, message = HTTP_OK_MESSAGE, response = AuthenticationResponse.class),
        @ApiResponse(code = HTTP_BAD_REQUEST, message = HTTP_BAD_REQUEST_MESSAGE, response = ErrorResponse.class),
        @ApiResponse(code = HTTP_UNAUTHORIZED, message = HTTP_UNAUTHORIZED_MESSAGE, response = ErrorResponse.class)
    })
    @Consumes(MediaType.APPLICATION_JSON)
    AuthenticationResponse postAuthentication(
        @ApiParam(name = "type", value = "The type of authentication request.", required = true)
        String type,

        @ApiParam(name = "email", value = "The email address of the customer, required for type = credentials.")
        String email,

        @ApiParam(name = "password", value = "The password for this customer, required for type = credentials.")
        String password
    );

    @Deprecated
    @POST
    @Path("/login")
    @ApiOperation(
        nickname = "postCustomerLogin",
        value = "Performs a customer login, potentially merging an anonymous cart with a customer cart."
    )
    @ApiResponses(value = {
        @ApiResponse(code = HTTP_OK, message = HTTP_OK_MESSAGE, response = LoginResult.class),
        @ApiResponse(code = HTTP_BAD_REQUEST, message = HTTP_BAD_REQUEST_MESSAGE, response = ErrorResponse.class),
        @ApiResponse(code = HTTP_UNAUTHORIZED, message = HTTP_UNAUTHORIZED_MESSAGE, response = ErrorResponse.class)
    })
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    LoginResult login(
        @ApiParam(value = "The email address of the customer.", required = true)
        @FormParam("email")
        String email,
        
        @ApiParam(value = "The password for this customer.", required = true)
        @FormParam("password")
        String password,
        
        @ApiParam(value = "An optional anonymous cart id to be merged during the login process.")
        @FormParam("anonymousCartId")
        String anonymousCartId,

        @ApiParam(value = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE) String acceptLanguage
    );
}
