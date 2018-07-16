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

import java.util.Date;
import java.util.List;

import com.adobe.commerce.cif.model.common.Address;
import com.adobe.commerce.cif.model.common.Payment;
import com.adobe.commerce.cif.model.common.Price;
import com.adobe.commerce.cif.model.common.TaxInfo;
import com.adobe.commerce.cif.model.discount.Discount;
import io.swagger.annotations.ApiModelProperty;

public class Cart {

    @ApiModelProperty(value = "The id for the cart.", required = true)
    protected String id;

    @ApiModelProperty(value = "The list of the entries in the cart.", required = true)
    protected List<CartEntry> cartEntries;

    @ApiModelProperty(value = "If set, this defines the customer owning this cart. If not set, the cart is an anonymous cart.")
    protected String customerId;
    
    @ApiModelProperty(value = "The net total price for the cart, including discounts, and shipping, but excluding any taxes.")
    protected Price netTotalPrice;
    
    @ApiModelProperty(value = "The gross total price for the cart, including discounts, shipping, and all taxes.")
    protected Price grossTotalPrice;
    
    @ApiModelProperty(value = "The product subtotal for the cart, including discounts and with or without taxes depending if the product prices include taxes or not." +
                              "Until a shipping address is set, this field is typically used as the temporary cart total until it is known if prices include taxes or not.")
    protected Price totalProductPrice;

    @ApiModelProperty(value = "The cart tax info, including cart entries tax and shipping info tax.")
    protected TaxInfo cartTaxInfo;

    @ApiModelProperty(value = "Indicates if taxes are included or not in all the prices.")
    protected Boolean taxIncludedInPrices;

    @ApiModelProperty(value = "The date when this cart was created.")
    protected Date createdDate;

    @ApiModelProperty(value = "The date when this cart was last modified.")
    protected Date lastModifiedDate;

    @ApiModelProperty(value = "The shipping address for the cart products.")
    protected Address shippingAddress;

    @ApiModelProperty(value = "The shipping info for the cart.")
    protected ShippingInfo shippingInfo;

    @ApiModelProperty(value = "A list of all applied discounts.")
    protected List<Discount> discounts;

    @ApiModelProperty(value = "The billing address for the cart.")
    protected Address billingAddress;

    @ApiModelProperty(value = "The payment details for the cart.")
    protected Payment payment;

    @ApiModelProperty(value = "The currency for the cart.")
    protected String currency;

    @ApiModelProperty(value = "A list of all coupons of the cart.")
    protected List<Coupon> coupons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartEntry> getCartEntries() {
        return cartEntries;
    }

    public void setCartEntries(List<CartEntry> cartEntries) {
        this.cartEntries = cartEntries;
    }
    
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public Price getNetTotalPrice() {
        return netTotalPrice;
    }

    public void setNetTotalPrice(Price netTotalPrice) {
        this.netTotalPrice = netTotalPrice;
    }

    public Price getGrossTotalPrice() {
        return grossTotalPrice;
    }

    public void setGrossTotalPrice(Price grossTotalPrice) {
        this.grossTotalPrice = grossTotalPrice;
    }
    
    public Price getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(Price totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public TaxInfo getCartTaxInfo() {
        return cartTaxInfo;
    }

    public void setCartTaxInfo(TaxInfo cartTaxInfo) {
        this.cartTaxInfo = cartTaxInfo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getTaxIncludedInPrices() {
        return taxIncludedInPrices;
    }

    public void setTaxIncludedInPrices(Boolean taxIncludedInPrices) {
        this.taxIncludedInPrices = taxIncludedInPrices;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
