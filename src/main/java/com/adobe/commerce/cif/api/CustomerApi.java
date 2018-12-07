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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.adobe.commerce.cif.api.Constants.ACCEPT_LANGUAGE;
import static com.adobe.commerce.cif.api.Constants.ACCEPT_LANGUAGE_DESC;
import static com.adobe.commerce.cif.api.Constants.HTTP_BAD_REQUEST;
import static com.adobe.commerce.cif.api.Constants.HTTP_BAD_REQUEST_MESSAGE;
import static com.adobe.commerce.cif.api.Constants.HTTP_FORBIDDEN;
import static com.adobe.commerce.cif.api.Constants.HTTP_FORBIDDEN_MESSAGE;
import static com.adobe.commerce.cif.api.Constants.HTTP_NOT_FOUND;
import static com.adobe.commerce.cif.api.Constants.HTTP_NOT_FOUND_MESSAGE;
import static com.adobe.commerce.cif.api.Constants.HTTP_OK;
import static com.adobe.commerce.cif.api.Constants.HTTP_OK_MESSAGE;

@Path("/customers")
@Tag(name = "customers")
@Produces(MediaType.APPLICATION_JSON)
public interface CustomerApi {

    @GET
    @Path("/{id}")
    @Operation(summary = "Returns a customer by ID.")
    @ApiResponse(
        responseCode = HTTP_BAD_REQUEST,
        description = HTTP_BAD_REQUEST_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_NOT_FOUND,
        description = HTTP_NOT_FOUND_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    Customer getCustomerById(
        @Parameter(description = "The id of the customer.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    @POST
    @Path("/auth")
    @Operation(
        summary = "Performs an authentication request to obtain a customer access token.",
        description = "Currently two types are supported: \"guest\" returns a token for a new guest (non-authenticated) " +
            "customer and \"credentials\" authenticates a registered customer and returns a token upon successful " +
            "authentication otherwise results in an error."
    )
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_FORBIDDEN,
        description = HTTP_FORBIDDEN_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_NOT_FOUND,
        description = HTTP_NOT_FOUND_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @Consumes(MediaType.APPLICATION_JSON)
    AuthenticationResponse postAuthentication(
        @Parameter(description = "The type of authentication request.", required = true)
        String type,

        @Parameter(description = "The email address of the customer, required for type = credentials.")
        String email,

        @Parameter(description = "The password for this customer, required for type = credentials.")
        String password
    );

    @Deprecated
    @POST
    @Path("/login")
    @Operation(
        operationId = "postCustomerLogin",
        summary = "Performs a customer login, potentially merging an anonymous cart with a customer cart.",
        deprecated = true
    )
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_FORBIDDEN,
        description = HTTP_FORBIDDEN_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_NOT_FOUND,
        description = HTTP_NOT_FOUND_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    LoginResult login(
        @Parameter(description = "The email address of the customer.", required = true)
        @FormParam("email")
        String email,
        
        @Parameter(description = "The password for this customer.", required = true)
        @FormParam("password")
        String password,
        
        @Parameter(description = "An optional anonymous cart id to be merged during the login process.")
        @FormParam("anonymousCartId")
        String anonymousCartId,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );
}
