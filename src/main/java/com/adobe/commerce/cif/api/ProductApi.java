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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.adobe.commerce.cif.model.common.PagedResponse;
import com.adobe.commerce.cif.model.error.ErrorResponse;
import com.adobe.commerce.cif.model.product.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/products")
@Api(value = "/products")
@Produces(MediaType.APPLICATION_JSON)
public interface ProductApi {

    @GET
    @Path("/{id}")
    @ApiOperation(value = "Returns a product by ID.")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid ID supplied", response = ErrorResponse.class),
        @ApiResponse(code = 404, message = "Product not found", response = ErrorResponse.class)
    })
    public Product getProductById(
        @ApiParam(value = "The ID of the product to return", required = true)
        @PathParam("id")
        String id
    );

    @GET
    @Path("/search")
    @ApiOperation(value = "Searches products based on the given query parameters")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid search parameters supplied", response = ErrorResponse.class)
    })
    public PagedResponse<Product> searchProducts(
        @ApiParam(value = "The text to search if this is a full-text search.")
        @QueryParam(value = "text")
        String text,
        
        @ApiParam(
            value = "The search filters, separated by the pipe character (OpenWhisk does not yet support the 'multi' collection format).",
            collectionFormat = "pipes"
        )
        @QueryParam(value = "filter")
        String[] filter,

        @ApiParam(
                value = "The facets values used for filtering, separated by the pipe character (OpenWhisk does not yet support the 'multi' collection format).",
                collectionFormat = "pipes"
        )
        @QueryParam(value = "selectedFacets")
                String[] selectedFacets,

        @ApiParam(
                value = "The facets list to be calculated, separated by the pipe character (OpenWhisk does not yet support the 'multi' collection format).",
                collectionFormat = "pipes"
        )
        @QueryParam(value = "queryFacets")
                String[] queryFacets,

        @ApiParam(
            value = "The sort attributes and direction, separated by the pipe character (OpenWhisk does not yet support the 'multi' collection format).",
            collectionFormat = "pipes"
        )
        @QueryParam(value = "sort")
        String[] sort,
        
        @ApiParam(value = "Defines the number of products to skip when returning the result.")
        @QueryParam(value = "offset")
        @Min(value = 0)
        Integer offset,

        @ApiParam(value = "Defines the maximum number of products to be returned by that search.")
        @QueryParam(value = "limit") 
        @Min(value = 0)
        Integer limit
    );
}
