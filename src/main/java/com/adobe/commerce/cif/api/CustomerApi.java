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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.adobe.commerce.cif.model.customer.Customer;
import com.adobe.commerce.cif.model.customer.LoginResult;
import com.adobe.commerce.cif.model.error.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/customers")
@Api(value = "/customers")
@Produces(MediaType.APPLICATION_JSON)
public interface CustomerApi {

    @GET
    @Path("/{id}")
    @ApiOperation(value = "Returns a customer by ID.")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ErrorResponse.class),
        @ApiResponse(code = 404, message = "Customer not found", response = ErrorResponse.class)
    })
    Customer getCustomerById(
        @ApiParam(value = "The id of the customer", required = true)
        @PathParam("id")
        String id
    );
    
    @POST
    @Path("/login")
    @ApiOperation(
        nickname = "postCustomerLogin",
        value = "Performs a customer login, potentially merging an anonymous cart with a customer cart."
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK.", response = LoginResult.class),
        @ApiResponse(code = 400, message = "Invalid customer credentials", response = ErrorResponse.class)
    })
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    LoginResult login(
        @ApiParam(value = "The email address of the customer", required = true)
        @FormParam("email")
        String email,
        
        @ApiParam(value = "The password for this customer", required = true)
        @FormParam("password")
        String password,
        
        @ApiParam(value = "An optional anonymous cart id to be merged during the login process")
        @FormParam("anonymousCartId")
        String anonymousCartId
    );
}
