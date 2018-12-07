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

import com.adobe.commerce.cif.model.common.MoneyValue;
import com.adobe.commerce.cif.model.common.TaxInfo;
import com.adobe.commerce.cif.model.discount.Discount;
import com.adobe.commerce.cif.model.product.ProductVariant;
import io.swagger.v3.oas.annotations.media.Schema;

public class CartEntry {

    @Schema(description = "The id for the entry.", required = true)
    protected String id;

    @Schema(description = "The quantity for the entry.", required = true)
    protected Integer quantity;

    @Schema(description = "The ProductVariant for the entry.", required = true)
    protected ProductVariant productVariant;

    @Schema(description = "The product variant item price.", required = true)
    protected MoneyValue unitPrice;

    @Schema(description = "A list of all applied discounts.")
    protected List<Discount> discounts;

    @Schema(description = "The calculated cart entry price. May or may not include taxes, depending on the tax policy.", required = true)
    protected MoneyValue price;

    @Schema(description = "The cart entry price after all discounts have been applied.")
    protected MoneyValue discountedPrice;

    @Schema(description = "The cart entry tax info. Until a shipping address is set, this field is typically not set.")
    protected TaxInfo taxInfo;

    @Schema(description = "Cart entry type.", required = true)
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

    public MoneyValue getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(MoneyValue unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public MoneyValue getPrice() {
        return price;
    }

    public void setPrice(MoneyValue price) {
        this.price = price;
    }

    public MoneyValue getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(MoneyValue discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public CartEntryType getType() {
        return type;
    }

    public void setType(CartEntryType type) {
        this.type = type;
    }

    public TaxInfo getTaxInfo() {
        return taxInfo;
    }

    public void setTaxInfo(TaxInfo taxInfo) {
        this.taxInfo = taxInfo;
    }
}
