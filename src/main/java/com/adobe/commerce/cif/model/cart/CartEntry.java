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

package com.adobe.commerce.cif.model.cart;

import java.util.List;

import com.adobe.commerce.cif.model.common.Price;
import com.adobe.commerce.cif.model.common.TaxInfo;
import com.adobe.commerce.cif.model.discount.Discount;
import com.adobe.commerce.cif.model.product.ProductVariant;
import io.swagger.annotations.ApiModelProperty;

public class CartEntry {

    @ApiModelProperty(value = "The id for the entry.", required = true)
    protected String id;

    @ApiModelProperty(value = "The quantity for the entry.", required = true)
    protected Integer quantity;

    @ApiModelProperty(value = "The ProductVariant for the entry.", required = true)
    protected ProductVariant productVariant;

    @ApiModelProperty(value = "The product variant item price.")
    protected Price unitPrice;

    @ApiModelProperty(value = "A list of all applied discounts")
    protected List<Discount> discounts;

    @ApiModelProperty(value = "The calculated cart entry price. May or may not include taxes, depending on the tax policy.")
    protected Price cartEntryPrice;

    @ApiModelProperty(value = "The cart entry price after all discounts have been applied.")
    protected Price discountedCartEntryPrice;

    @ApiModelProperty(value = "The cart entry tax info.")
    protected TaxInfo cartEntryTaxInfo;

    @ApiModelProperty(value = "Cart entry type.")
    protected CartEntryType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
    }

    public Price getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Price unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public Price getCartEntryPrice() {
        return cartEntryPrice;
    }

    public void setCartEntryPrice(Price cartEntryPrice) {
        this.cartEntryPrice = cartEntryPrice;
    }

    public Price getDiscountedCartEntryPrice() {
        return discountedCartEntryPrice;
    }

    public void setDiscountedCartEntryPrice(Price discountedCartEntryPrice) {
        this.discountedCartEntryPrice = discountedCartEntryPrice;
    }

    public CartEntryType getType() {
        return type;
    }

    public void setType(CartEntryType type) {
        this.type = type;
    }

    public TaxInfo getCartEntryTaxInfo() {
        return cartEntryTaxInfo;
    }

    public void setCartEntryTaxInfo(TaxInfo cartEntryTaxInfo) {
        this.cartEntryTaxInfo = cartEntryTaxInfo;
    }
}
