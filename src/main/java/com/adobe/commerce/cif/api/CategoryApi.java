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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.adobe.commerce.cif.model.category.Category;
import com.adobe.commerce.cif.model.common.PagedResponse;
import com.adobe.commerce.cif.model.error.ErrorResponse;
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
import static com.adobe.commerce.cif.api.Constants.HTTP_NOT_FOUND;
import static com.adobe.commerce.cif.api.Constants.HTTP_NOT_FOUND_MESSAGE;

@Path("/categories")
@Tag(name = "categories")
@Produces(MediaType.APPLICATION_JSON)
public interface CategoryApi {

    @GET
    @Path("/{id}")
    @Operation(summary = "Returns a category by ID.")
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
    Category getCategoryById(
        @Parameter(description = "The ID of the category to return.", required = true)
        @PathParam("id") String id,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @GET
    @Path("/slug/{slug}")
    @Operation(summary = "Returns a category by slug.")
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
    Category getCategoryBySlug(
        @Parameter(description = "The slug of the category to return. The slug needs to uniquely identify the category and can be a path that contains slashes.", required = true)
        @PathParam("slug") String slug,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );
    
    
    @GET
    @Path("/")
    @Operation(summary = "Returns the entire category structure or a subset of it depending on pagination.")
    @ApiResponse(
        responseCode = HTTP_BAD_REQUEST,
        description = HTTP_BAD_REQUEST_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    PagedResponse<Category> getCategories(
        @Parameter(
            description = "The sort attributes and direction, separated by the pipe character (OpenWhisk does not yet support the 'multi' collection format).",
            style = ParameterStyle.PIPEDELIMITED
        )
        @QueryParam(value = "sort")
        String[] sort,
        
        @Parameter(description = "Defines the number of categories to skip when returning the result.")
        @QueryParam(value = "offset")
        @Schema(minimum = "0")
        Integer offset,

        @Parameter(description = "Defines the maximum number of categories to be returned by that query.")
        @QueryParam(value = "limit") 
        @Schema(minimum = "0")
        Integer limit,
        
        @Parameter(
            description = "Defines if the request should return either a flat or tree category structure.",
            schema = @Schema(allowableValues = "flat, tree", defaultValue = "flat")
        )
        @QueryParam(value = "type")
        String type,

        @Parameter(description = "Defines the maximum depth of the returned categories. No value means infinite depth, 0 means only the root categories.")
        @QueryParam(value = "depth") 
        @Schema(minimum = "0")
        Integer depth,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );
}
