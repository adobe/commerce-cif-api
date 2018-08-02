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

import javax.validation.constraints.Min;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.adobe.commerce.cif.model.common.PagedResponse;
import com.adobe.commerce.cif.model.error.ErrorResponse;
import com.adobe.commerce.cif.model.inventory.InventoryItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.adobe.commerce.cif.api.Constants.ACCEPT_LANGUAGE;
import static com.adobe.commerce.cif.api.Constants.ACCEPT_LANGUAGE_DESC;
import static com.adobe.commerce.cif.api.Constants.HTTP_BAD_REQUEST;
import static com.adobe.commerce.cif.api.Constants.HTTP_BAD_REQUEST_MESSAGE;

@Path("/inventory")
@Api(value = "/inventory")
@Produces(MediaType.APPLICATION_JSON)
public interface InventoryApi {

    @GET
    @Path("/query")
    @ApiOperation(value = "Queries inventory based on the given query parameters.")
    @ApiResponses(value = {
        @ApiResponse(code = HTTP_BAD_REQUEST, message = HTTP_BAD_REQUEST_MESSAGE, response = ErrorResponse.class)
    })
    PagedResponse<InventoryItem> query(

            @ApiParam(value = "The list of product identifiers for which the inventory will be queried.", collectionFormat = "pipes")
            @QueryParam(value = "productId")
            String[] productId,

            @ApiParam(value = "The list of scopes for which the inventory will be queried.", collectionFormat = "pipes")
            @QueryParam(value = "scope")
            String[] scope,

            @ApiParam(value = "Defines the number of inventory items to skip when returning the result.")
            @QueryParam(value = "offset")
            @Min(value = 0)
            Integer offset,

            @ApiParam(value = "Defines the maximum number of inventory items to be returned in the result.")
            @QueryParam(value = "limit")
            @Min(value = 0)
            Integer limit,

            @ApiParam(value = ACCEPT_LANGUAGE_DESC)
            @HeaderParam(ACCEPT_LANGUAGE) String acceptLanguage
    );
}
