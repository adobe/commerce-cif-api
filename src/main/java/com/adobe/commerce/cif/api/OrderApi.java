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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.adobe.commerce.cif.model.cart.Order;
import com.adobe.commerce.cif.model.error.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/orders")
@Api(value = "/orders")
@Produces(MediaType.APPLICATION_JSON)
public interface OrderApi {

    @POST
    @Path("/")
    @ApiOperation(value = "Creates an order based on a cart.")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid ID supplied.", response = ErrorResponse.class),
        @ApiResponse(code = 404, message = "Cart not found.", response = ErrorResponse.class),
        @ApiResponse(code = 403, message = "Not allowed to create the order.", response = ErrorResponse.class)
    })
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    Order postOrder(
        @ApiParam(value = "The id of the cart from which the order will be created.")
        @FormParam("cartId") String cartId,

        @ApiParam(value = "If the cart belongs to a customer, this must be set to the customer id owning that cart.")
        @FormParam("customerId") String customerId);
}
