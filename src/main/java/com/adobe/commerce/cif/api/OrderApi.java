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
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.adobe.commerce.cif.model.cart.Order;
import com.adobe.commerce.cif.model.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.adobe.commerce.cif.api.Constants.ACCEPT_LANGUAGE;
import static com.adobe.commerce.cif.api.Constants.ACCEPT_LANGUAGE_DESC;
import static com.adobe.commerce.cif.api.Constants.HTTP_BAD_REQUEST;
import static com.adobe.commerce.cif.api.Constants.HTTP_BAD_REQUEST_MESSAGE;
import static com.adobe.commerce.cif.api.Constants.HTTP_CREATED;
import static com.adobe.commerce.cif.api.Constants.HTTP_CREATED_MESSAGE;
import static com.adobe.commerce.cif.api.Constants.HTTP_FORBIDDEN;
import static com.adobe.commerce.cif.api.Constants.HTTP_FORBIDDEN_MESSAGE;
import static com.adobe.commerce.cif.api.Constants.HTTP_NOT_FOUND;
import static com.adobe.commerce.cif.api.Constants.HTTP_NOT_FOUND_MESSAGE;

@Path("/orders")
@Tag(name = "orders")
@Produces(MediaType.APPLICATION_JSON)
public interface OrderApi {

    @POST
    @Path("/")
    @Operation(summary = "Creates an order based on a cart.")
    @ApiResponse(
        responseCode = HTTP_CREATED,
        description = HTTP_CREATED_MESSAGE,
        content = @Content(schema = @Schema(implementation = Order.class)),
        headers = {
            @Header(name = "Location", description = "Location of the newly created order.")
        }
    )
    @ApiResponse(
        responseCode = HTTP_BAD_REQUEST,
        description = HTTP_BAD_REQUEST_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
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
    Order postOrder(
        @Parameter(description = "The id of the cart from which the order will be created.")
        @FormParam("cartId")
        String cartId,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );
}
