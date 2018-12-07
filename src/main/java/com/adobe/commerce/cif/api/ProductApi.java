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

import com.adobe.commerce.cif.model.common.PagedResponse;
import com.adobe.commerce.cif.model.error.ErrorResponse;
import com.adobe.commerce.cif.model.product.Product;
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
import static com.adobe.commerce.cif.api.Constants.HTTP_OK;
import static com.adobe.commerce.cif.api.Constants.HTTP_OK_MESSAGE;

@Path("/products")
@Tag(name = "products")
@Produces(MediaType.APPLICATION_JSON)
public interface ProductApi {
    
    @GET
    @Path("/{id}")
    @Operation(summary = "Returns a product by ID.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Product.class))
    )
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
    Product getProductById(
        @Parameter(description = "The ID of the product to return.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @GET
    @Path("/slug/{slug}")
    @Operation(summary = "Returns a product by slug.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Product.class))
    )
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
    Product getProductBySlug(
        @Parameter(description = "The slug of the product to return. The slug needs to uniquely identify the product and can be a path that contains slashes.", required = true)
        @PathParam("slug")
        String slug,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    // Workaround generic type for @Schema(implementation) annotation
    public static class PagedResponseProduct extends PagedResponse<Product> {}
    
    @GET
    @Path("/search")
    @Operation(summary = "Searches products based on the given query parameters.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = PagedResponseProduct.class))
    )
    @ApiResponse(
        responseCode = HTTP_BAD_REQUEST,
        description = HTTP_BAD_REQUEST_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    PagedResponse<Product> searchProducts(
        @Parameter(description = "The text to search if this is a full-text search.")
        @QueryParam(value = "text")
        String text,
        
        @Parameter(
            description = "The search filters, separated by the pipe character (OpenWhisk does not yet support the 'multi' collection format).",
            style = ParameterStyle.PIPEDELIMITED
        )
        @QueryParam(value = "filter")
        String[] filter,

        @Parameter(
            description = "The facets values used for filtering, separated by the pipe character (OpenWhisk does not yet support the 'multi' collection format).",
            style = ParameterStyle.PIPEDELIMITED
        )
        @QueryParam(value = "selectedFacets")
        String[] selectedFacets,

        @Parameter(
             description = "The facets list to be calculated, separated by the pipe character (OpenWhisk does not yet support the 'multi' collection format).",
             style = ParameterStyle.PIPEDELIMITED
        )
        @QueryParam(value = "queryFacets")
        String[] queryFacets,

        @Parameter(
             description = "The sort attributes and direction, separated by the pipe character (OpenWhisk does not yet support the 'multi' collection format).",
             style = ParameterStyle.PIPEDELIMITED
        )
        @QueryParam(value = "sort")
        String[] sort,
        
        @Parameter(description = "Defines the number of products to skip when returning the result.")
        @QueryParam(value = "offset")
        @Schema(minimum = "0")
        Integer offset,

        @Parameter(description = "Defines the maximum number of products to be returned by that search.")
        @QueryParam(value = "limit") 
        @Schema(minimum = "0")
        Integer limit,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );
}
