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

import java.util.List;

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
import javax.ws.rs.core.MediaType;

import com.adobe.commerce.cif.model.cart.Cart;
import com.adobe.commerce.cif.model.cart.ShippingMethod;
import com.adobe.commerce.cif.model.common.PaymentMethod;
import com.adobe.commerce.cif.model.error.ErrorResponse;
import com.adobe.commerce.cif.model.wrapper.AddressWrapper;
import com.adobe.commerce.cif.model.wrapper.PaymentWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
import static com.adobe.commerce.cif.api.Constants.HTTP_NO_CONTENT;
import static com.adobe.commerce.cif.api.Constants.HTTP_NO_CONTENT_MESSAGE;
import static com.adobe.commerce.cif.api.Constants.HTTP_OK;
import static com.adobe.commerce.cif.api.Constants.HTTP_OK_MESSAGE;
import static com.adobe.commerce.cif.api.Constants.HTTP_UNAUTHORIZED;
import static com.adobe.commerce.cif.api.Constants.HTTP_UNAUTHORIZED_MESSAGE;

@Path("/carts")
@Tag(name = "carts")
@Produces(MediaType.APPLICATION_JSON)
public interface CartApi {

    @POST
    @Path("/")
    @Operation(summary = "Creates an empty cart. For convenience it also adds a cart entry when product variant id " +
        "and quantity are provided.")
    @ApiResponse(
        responseCode = HTTP_CREATED,
        description = HTTP_CREATED_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class)),
        headers = {
            @Header(name = "Location", description = "Location of the newly created cart.")
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
    Cart postCart(
        @Parameter(description = "Three-digit currency code.", required = true)
        @FormParam("currency")
        String currency,

        @Parameter(description = "The product variant id to be added to the cart entry.")
        @FormParam("productVariantId")
        String productVariantId,

        @Parameter(description = "The quantity for the product variant.")
        @FormParam("quantity")
        int quantity,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @GET
    @Path("/{id}")
    @Operation(summary = "Returns a cart by ID.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class))
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
    Cart getCart(
        @Parameter(description = "The ID of the cart to be returned.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes the cart.")
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
    void deleteCart(
        @Parameter(description = "The ID of the cart to be removed.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @POST
    @Path("/{id}/entries")
    @Operation(summary = "Adds a new cart entry to an existing cart.")
    @ApiResponse(
        responseCode = HTTP_CREATED,
        description = HTTP_CREATED_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class)),
        headers = {
            @Header(name = "Location", description = "Location of the newly created cart entry.")
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
    Cart postCartEntry(
        @Parameter(description = "The ID of the cart for the new entry", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = "The product variant id to be added to the cart entry. If product variant exists in the" +
            " cart then the cart entry quantity is increased with the provided quantity.", required = true)
        @FormParam("productVariantId")
        String productVariantId,

        @Parameter(description = "The quantity for the new entry.", required = true)
        @FormParam("quantity")
        @Schema(minimum = "0")
        int quantity,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @PUT
    @Path("/{id}/entries/{cartEntryId}")
    @Operation(summary = "Updates an existing cart entry.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class))
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
    Cart putCartEntry(
        @Parameter(description = "The ID of the cart.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = "The cart entry id to be updated.", required = true)
        @PathParam("cartEntryId")
        String cartEntryId,

        @Parameter(description = "The cart entry quantity. When quantity is 0 the entry will be removed otherwise the " +
            "value is used as new quantity.", required = true)
        @FormParam("quantity")
        @Schema(minimum = "0")
        int quantity,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @DELETE
    @Path("/{id}/entries/{cartEntryId}")
    @Operation(summary = "Removes a cart entry from the cart.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class))
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
    Cart deleteCartEntry(
        @Parameter(description = "The ID of the cart.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = "The cart entry id to be removed.", required = true)
        @PathParam("cartEntryId")
        String cartEntryId,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @POST
    @Path("/{id}/shippingaddress")
    @Operation(summary = "Sets the shipping address for the cart.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class))
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
    Cart postShippingAddress(
        @Parameter(description = "The ID of the cart for the shipping address.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = "The shipping address", required = true)
        AddressWrapper addressWrapper,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @DELETE
    @Path("/{id}/shippingaddress")
    @Operation(summary = "Deletes the shipping address for the cart.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class))
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
    Cart deleteShippingAddress(
        @Parameter(description = "The ID of the cart for which the shipping address will be deleted.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @POST
    @Path("/{id}/billingaddress")
    @Operation(summary = "Sets the billing address for the cart.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class))
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
    @Consumes(MediaType.APPLICATION_JSON)
    Cart postBillingAddress(
        @Parameter(description = "The ID of the cart for the billing address.", required = true)
        @PathParam("id") String id,

        @Parameter(description = "The billing address", required = true)
        AddressWrapper addressWrapper,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE) String acceptLanguage);

    
    @DELETE
    @Path("/{id}/billingaddress")
    @Operation(summary = "Deletes the billing address for the cart.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class))
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
    Cart deleteBillingAddress(
        @Parameter(description = "The ID of the cart for which the billing address will be deleted.", required = true)
        @PathParam("id") String id,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE) String acceptLanguage);

    
    @POST
    @Path("/{id}/shippingmethod")
    @Operation(summary = "Updates the shipping method for the cart.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class))
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
    Cart postShippingMethod(
        @Parameter(description = "The ID of the cart for which the shipping method will be updated.", required = true)
        @PathParam("id") String id,

        @Parameter(description = "The shipping method id.", required = true)
        @FormParam("shippingMethodId") String shippingMethodId,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE) String acceptLanguage);

    
    @DELETE
    @Path("/{id}/shippingmethod")
    @Operation(summary = "Deletes the shipping method for the cart.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class))
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
    Cart deleteShippingMethod(
        @Parameter(description = "The ID of the cart for which the shipping method will be deleted.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    // Workaround generic type for @Schema(implementation) annotation
    public static interface ShippingMethodList extends List<ShippingMethod> {}
    
    @GET
    @Path("/{id}/shippingmethods")
    @Operation(summary = "Retrieves the available shipping methods for the current cart.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = ShippingMethodList.class))
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
    List<ShippingMethod> getShippingMethods(
        @Parameter(description = "The id of the cart.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @Deprecated
    @POST
    @Path("/{id}/payment")
    @Operation(summary = "DEPRECATED. Creates a payment for this shopping cart.", deprecated = true)
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class))
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
    @Consumes(MediaType.APPLICATION_JSON)
    Cart postPayment(
        @Parameter(description = "The ID of the cart for which the payment will be set.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = "The payment to create. If the cart belongs to a customer, the customer id must be set.", required = true)
        PaymentWrapper paymentWrapper,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @POST
    @Path("/{id}/payments")
    @Operation(summary = "Adds a payment to this shopping cart.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class))
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
    @Consumes(MediaType.APPLICATION_JSON)
    Cart postCartPayment(
        @Parameter(description = "The ID of the cart for which the payment will be set.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = "The payment to create. If the cart belongs to a customer, the customer id must be set.", required = true)
        PaymentWrapper paymentWrapper,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage);

    
    @Deprecated
    @DELETE
    @Path("/{id}/payment")
    @Operation(summary = "DEPRECATED. Removes the payment from the shopping cart.", deprecated = true)
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class))
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
    Cart deletePayment(
        @Parameter(description = "The ID of the cart for which the payment will be deleted.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @DELETE
    @Path("/{id}/payments/{paymentId}")
    @Operation(summary = "Removes the payment with the given ID from the shopping cart.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class))
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
    Cart deleteCartPayment(
        @Parameter(description = "The ID of the cart for which the payment will be deleted.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = "The ID of the payment which will be deleted.", required = true)
        @PathParam("paymentId")
        String paymentId,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    // Workaround generic type for @Schema(implementation) annotation
    public static interface PaymentMethodList extends List<PaymentMethod> {}
    
    @GET
    @Path("/{id}/paymentmethods")
    @Operation(summary = "Retrieves the available payment methods for the current cart.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = PaymentMethodList.class))
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
    List<PaymentMethod> getPaymentMethods(
        @Parameter(description = "The id of the cart.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @POST
    @Path("/{id}/coupons")
    @Operation(summary = "Adds a coupon to the shopping cart.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class))
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
    Cart postCoupon(
        @Parameter(description = "The id of the cart to which the coupon will be added.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = "The coupon code.", required = true)
        @FormParam("code")
        String code,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE)
        String acceptLanguage
    );

    
    @DELETE
    @Path("/{id}/coupons/{couponId}")
    @Operation(summary = "Deletes a coupon from the shopping cart.")
    @ApiResponse(
        responseCode = HTTP_OK,
        description = HTTP_OK_MESSAGE,
        content = @Content(schema = @Schema(implementation = Cart.class))
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
    Cart deleteCoupon(
        @Parameter(description = "The id of the cart for which the coupon will be deleted.", required = true)
        @PathParam("id")
        String id,

        @Parameter(description = "The id of the coupon that will be deleted.", required = true)
        @PathParam("couponId")
        String couponId,

        @Parameter(description = ACCEPT_LANGUAGE_DESC, in = ParameterIn.HEADER)
        String acceptLanguage
    );

}
