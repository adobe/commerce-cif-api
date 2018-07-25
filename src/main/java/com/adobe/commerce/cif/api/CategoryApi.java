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

import com.adobe.commerce.cif.model.category.Category;
import com.adobe.commerce.cif.model.common.PagedResponse;
import com.adobe.commerce.cif.model.error.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.adobe.commerce.cif.api.Constants.HTTP_BAD_REQUEST;
import static com.adobe.commerce.cif.api.Constants.HTTP_BAD_REQUEST_MESSAGE;
import static com.adobe.commerce.cif.api.Constants.HTTP_NOT_FOUND;
import static com.adobe.commerce.cif.api.Constants.HTTP_NOT_FOUND_MESSAGE;

@Path("/categories")
@Api(value = "/categories")
@Produces(MediaType.APPLICATION_JSON)
public interface CategoryApi {

    @GET
    @Path("/{id}")
    @ApiOperation(value = "Returns a category by ID.")
    @ApiResponses(value = {
        @ApiResponse(code = HTTP_BAD_REQUEST, message = HTTP_BAD_REQUEST_MESSAGE , response = ErrorResponse.class),
        @ApiResponse(code = HTTP_NOT_FOUND, message = HTTP_NOT_FOUND_MESSAGE, response = ErrorResponse.class)
    })
    Category getCategoryById(
        @ApiParam(value = "The ID of the category to return.", required = true)
        @PathParam("id") String id
    );
    
    @GET
    @Path("/")
    @ApiOperation(value = "Returns the entire category structure or a subset of it depending on pagination.")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_BAD_REQUEST, message = HTTP_BAD_REQUEST_MESSAGE, response = ErrorResponse.class)
    })
    PagedResponse<Category> getCategories(
        @ApiParam(
            value = "The sort attributes and direction, separated by the pipe character (OpenWhisk does not yet support the 'multi' collection format).",
            collectionFormat = "pipes"
        )
        @QueryParam(value = "sort")
        String[] sort,
        
        @ApiParam(value = "Defines the number of categories to skip when returning the result.")
        @QueryParam(value = "offset")
        @Min(value = 0)
        Integer offset,

        @ApiParam(value = "Defines the maximum number of categories to be returned by that query.")
        @QueryParam(value = "limit") 
        @Min(value = 0)
        Integer limit,
        
        @ApiParam(
            value = "Defines if the request should return either a flat or tree category structure.",
            allowableValues = "flat, tree",
            defaultValue = "flat"
        )
        @QueryParam(value = "type")
        String type,

        @ApiParam(value = "Defines the maximum depth of the returned categories. No value means infinite depth, 0 means only the root categories.")
        @QueryParam(value = "depth") 
        @Min(value = 0)
        Integer depth
    );
}
