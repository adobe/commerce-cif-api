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
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.adobe.commerce.cif.model.common.PagedResponse;
import com.adobe.commerce.cif.model.error.ErrorResponse;
import com.adobe.commerce.cif.model.shoppinglist.ShoppingList;
import com.adobe.commerce.cif.model.shoppinglist.ShoppingListEntry;
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
import static com.adobe.commerce.cif.api.Constants.HTTP_NOT_FOUND;
import static com.adobe.commerce.cif.api.Constants.HTTP_NOT_FOUND_MESSAGE;
import static com.adobe.commerce.cif.api.Constants.HTTP_NO_CONTENT;
import static com.adobe.commerce.cif.api.Constants.HTTP_NO_CONTENT_MESSAGE;
import static com.adobe.commerce.cif.api.Constants.HTTP_UNAUTHORIZED;
import static com.adobe.commerce.cif.api.Constants.HTTP_UNAUTHORIZED_MESSAGE;

@Path("/shoppinglists")
@Tag(name = "shoppinglists")
@Produces(MediaType.APPLICATION_JSON)
public interface ShoppingListApi {

    @GET
    @Path("/")
    @Operation(
        summary = "Gets a users shopping lists.",
        description = "The entries property is empty for all shopping lists in the response. To retrieve entries, query a single shopping list."
    )
    @ApiResponse(
        responseCode = HTTP_BAD_REQUEST,
        description = HTTP_BAD_REQUEST_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_UNAUTHORIZED,
        description = HTTP_UNAUTHORIZED_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_NOT_FOUND,
        description = HTTP_NOT_FOUND_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    PagedResponse<ShoppingList> getShoppingLists(
        @Parameter(description = "Defines the number of shopping lists to skip.")
        @QueryParam(value = "offset")
        @Schema(minimum = "0")
        Integer offset,

        @Parameter(description = "Defines the maximum number of shopping lists to be returned.")
        @QueryParam(value = "limit")
        @Schema(minimum = "0")
        Integer limit,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @GET
    @Path("/{id}")
    @Operation(
        summary = "Gets a users shopping list with a given id."
    )
    @ApiResponse(
        responseCode = HTTP_BAD_REQUEST,
        description = HTTP_BAD_REQUEST_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_UNAUTHORIZED,
        description = HTTP_UNAUTHORIZED_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_NOT_FOUND,
        description = HTTP_NOT_FOUND_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    ShoppingList getShoppingList(
        @Parameter(description = "The id of the shopping list to return.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @POST
    @Path("/")
    @Operation(
        summary = "Creates a new shopping list for the current user.",
        description = "The name and description properties will be stored as localized strings in the language defined by the Content-Language header."
    )
    @ApiResponse(
        responseCode = HTTP_CREATED,
        description = HTTP_CREATED_MESSAGE,
        content = @Content(schema = @Schema(implementation = ShoppingList.class)),
        headers = {
            @Header(name = "Location", description = "Location of the newly created shopping list.")
        }
    )
    @ApiResponse(
        responseCode = HTTP_BAD_REQUEST,
        description = HTTP_BAD_REQUEST_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_UNAUTHORIZED,
        description = HTTP_UNAUTHORIZED_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_NOT_FOUND,
        description = HTTP_NOT_FOUND_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    ShoppingList postShoppingList(
        @Parameter(description = "Name of the shopping list.", required = true)
        @FormParam("name")
        String name,

        @Parameter(description = "Description of the shopping list.")
        @FormParam("description")
        String description,

        @Parameter(description = "Language of the shopping list.")
        @HeaderParam("Content-Language")
        String contentLanguage,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @PUT
    @Path("/{id}")
    @Operation(
        summary = "Replaces a shopping list with the given one."
    )
    @ApiResponse(
        responseCode = HTTP_BAD_REQUEST,
        description = HTTP_BAD_REQUEST_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_UNAUTHORIZED,
        description = HTTP_UNAUTHORIZED_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_NOT_FOUND,
        description = HTTP_NOT_FOUND_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    ShoppingList putShoppingList(
        @Parameter(description = "The id of the shopping list to replace.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = "Name of the shopping list.", required = true)
        @FormParam("name")
        String name,

        @Parameter(description = "Description of the shopping list.")
        @FormParam("description")
        String description,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @DELETE
    @Path("/{id}")
    @Operation(
        summary = "Deletes a shopping list."
    )
    @ApiResponse(
        responseCode = HTTP_NO_CONTENT,
        description = HTTP_NO_CONTENT_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_BAD_REQUEST,
        description = HTTP_BAD_REQUEST_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_UNAUTHORIZED,
        description = HTTP_UNAUTHORIZED_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_NOT_FOUND,
        description = HTTP_NOT_FOUND_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    void deleteShoppingList(
        @Parameter(description = "The id of the shopping list to be deleted.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @GET
    @Path("/{id}/entries")
    @Operation(summary = "Gets all entries from a shopping list.")
    @ApiResponse(
        responseCode = HTTP_BAD_REQUEST,
        description = HTTP_BAD_REQUEST_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_UNAUTHORIZED,
        description = HTTP_UNAUTHORIZED_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_NOT_FOUND,
        description = HTTP_NOT_FOUND_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    PagedResponse<ShoppingListEntry> getShoppingListEntries(
        @Parameter(description = "The id of the shopping list to return entries from.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = "Defines the number of entries to skip.")
        @QueryParam(value = "offset")
        @Schema(minimum = "0")
        Integer offset,

        @Parameter(description = "Defines the maximum number of entries to be returned.")
        @QueryParam(value = "limit")
        @Schema(minimum = "0")
        Integer limit,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @GET
    @Path("/{id}/entries/{entryId}")
    @Operation(summary = "Gets a single entry from a shopping list.")
    @ApiResponse(
        responseCode = HTTP_BAD_REQUEST,
        description = HTTP_BAD_REQUEST_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_UNAUTHORIZED,
        description = HTTP_UNAUTHORIZED_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_NOT_FOUND,
        description = HTTP_NOT_FOUND_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    ShoppingListEntry getShoppingListEntry(
        @Parameter(description = "The id of the shopping list.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = "The id of the shopping list entry to return.", required = true)
        @PathParam("entryId")
        String entryId,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @POST
    @Path("/{id}/entries")
    @Operation(summary = "Creates a new entry for a shopping list.")
    @ApiResponse(
        responseCode = HTTP_CREATED,
        description = HTTP_CREATED_MESSAGE,
        content = @Content(schema = @Schema(implementation = ShoppingList.class)),
        headers = {
            @Header(name = "Location", description = "Location of the newly created entry.")
        }
    )
    @ApiResponse(
        responseCode = HTTP_BAD_REQUEST,
        description = HTTP_BAD_REQUEST_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_UNAUTHORIZED,
        description = HTTP_UNAUTHORIZED_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_NOT_FOUND,
        description = HTTP_NOT_FOUND_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    ShoppingListEntry postShoppingListEntry(
        @Parameter(description = "The id of the shopping list.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = "The quantity for the new entry.", required = true)
        @FormParam("quantity")
        @Schema(minimum = "0")
        int quantity,

        @Parameter(description = "The product variant id to be added to the entry. If the product variant exists in the shopping list, its quantity is increased with the provided quantity.", required = true)
        @FormParam("productVariantId")
        String productVariantId,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @PUT
    @Path("/{id}/entries/{entryId}")
    @Operation(summary = "Replaces an entry with the given one.")
    @ApiResponse(
        responseCode = HTTP_BAD_REQUEST,
        description = HTTP_BAD_REQUEST_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_UNAUTHORIZED,
        description = HTTP_UNAUTHORIZED_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_NOT_FOUND,
        description = HTTP_NOT_FOUND_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    ShoppingListEntry putShoppingListEntry(
        @Parameter(description = "The id of the shopping list.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = "The id of the entry to replace.", required = true)
        @PathParam("entryId")
        String entryId,

        @Parameter(description = "The quantity for the new entry.", required = true)
        @FormParam("quantity")
        @Schema(minimum = "0")
        int quantity,

        @Parameter(description = "The product variant id to be added to the entry. If the product variant exists in another entry in the shopping list, this request fails.", required = true)
        @FormParam("productVariantId")
        String productVariantId,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @DELETE
    @Path("/{id}/entries/{entryId}")
    @Operation(summary = "Deletes an entry from a shopping list.")
    @ApiResponse(
        responseCode = HTTP_NO_CONTENT,
        description = HTTP_NO_CONTENT_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_BAD_REQUEST,
        description = HTTP_BAD_REQUEST_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_UNAUTHORIZED,
        description = HTTP_UNAUTHORIZED_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
        responseCode = HTTP_NOT_FOUND,
        description = HTTP_NOT_FOUND_MESSAGE,
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    )
    void deleteShoppingListEntry(
        @Parameter(description = "The id of the shopping list.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = "The id of the entry.", required = true)
        @PathParam("entryId")
        String entryId,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

}
