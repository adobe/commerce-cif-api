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

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.adobe.commerce.cif.model.common.PagedResponse;
import com.adobe.commerce.cif.model.error.ErrorResponse;
import com.adobe.commerce.cif.model.inventory.InventoryItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.adobe.commerce.cif.api.Constants.ACCEPT_LANGUAGE;
import static com.adobe.commerce.cif.api.Constants.ACCEPT_LANGUAGE_DESC;
import static com.adobe.commerce.cif.api.Constants.HTTP_BAD_REQUEST;
import static com.adobe.commerce.cif.api.Constants.HTTP_BAD_REQUEST_MESSAGE;

@Path("/inventory")
@Tag(name = "inventory")
@Produces(MediaType.APPLICATION_JSON)
public interface InventoryApi {

    @GET
    @Path("/query")
    @Operation(summary = "Queries inventory based on the given query parameters.")
    @ApiResponse(
        responseCode = HTTP_BAD_REQUEST,
        description = HTTP_BAD_REQUEST_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    PagedResponse<InventoryItem> query(

        @Parameter(
            description = "The list of product identifiers for which the inventory will be queried.",
            style = ParameterStyle.PIPEDELIMITED
        )
        @QueryParam(value = "productId")
        String[] productId,

        @Parameter(
            description = "The list of scopes for which the inventory will be queried.",
            style = ParameterStyle.PIPEDELIMITED
        )
        @QueryParam(value = "scope")
        String[] scope,

        @Parameter(description = "Defines the number of inventory items to skip when returning the result.")
        @QueryParam(value = "offset")
        @Schema(minimum = "0")
        Integer offset,

        @Parameter(description = "Defines the maximum number of inventory items to be returned in the result.")
        @QueryParam(value = "limit")
        @Schema(minimum = "0")
        Integer limit,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );
}
