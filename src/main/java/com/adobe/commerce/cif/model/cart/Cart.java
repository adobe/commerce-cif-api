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

import com.adobe.commerce.cif.model.common.Address;
import com.adobe.commerce.cif.model.common.ModelWithDates;
import com.adobe.commerce.cif.model.common.MoneyValue;
import com.adobe.commerce.cif.model.common.Payment;
import com.adobe.commerce.cif.model.common.TaxInfo;
import com.adobe.commerce.cif.model.discount.Discount;
import io.swagger.annotations.ApiModelProperty;

public class Cart extends ModelWithDates {

    @ApiModelProperty(value = "The id for the cart.", required = true)
    protected String id;

    @ApiModelProperty(value = "The list of the entries in the cart.", required = true)
    protected List<CartEntry> entries;

    @ApiModelProperty(value = "If set, this defines the customer owning this cart. If not set, the cart is an anonymous cart.")
    protected String customerId;
    
    @ApiModelProperty(value = "The net total price for the cart, including discounts, and shipping, but excluding any taxes. Until a shipping address is set, this field is typically not set.")
    protected MoneyValue netTotalPrice;
    
    @ApiModelProperty(value = "The gross total price for the cart, including discounts, shipping, and all taxes. Until a shipping address is set, this field is typically not set.")
    protected MoneyValue grossTotalPrice;
    
    @ApiModelProperty(value = "The product subtotal for the cart, including discounts and with or without taxes depending if the product prices include taxes or not." +
                              "Until a shipping address is set, this field is typically used as the temporary cart total until it is known if prices include taxes or not.", required = true)
    protected MoneyValue productTotalPrice;

    @ApiModelProperty(value = "The cart tax info, including cart entries tax and shipping info tax. Until a shipping address is set, this field is typically not set.")
    protected TaxInfo taxInfo;

    @ApiModelProperty(value = "Indicates if taxes are included or not in all the prices. Until a shipping address is set, this field is typically not set.")
    protected Boolean taxIncludedInPrices;

    @ApiModelProperty(value = "The shipping address for the cart products.")
    protected Address shippingAddress;

    @ApiModelProperty(value = "The shipping info for the cart.")
    protected ShippingInfo shippingInfo;

    @ApiModelProperty(value = "A list of all applied discounts.")
    protected List<Discount> discounts;

    @ApiModelProperty(value = "The billing address for the cart.")
    protected Address billingAddress;

    @ApiModelProperty(value = "DEPRECATED. The payment details for the cart.")
    @Deprecated
    protected Payment payment;

    @ApiModelProperty(value = "A list of payment details for the cart.")
    protected List<Payment> payments;

    @ApiModelProperty(value = "The currency for the cart.", required = true)
    protected String currency;

    @ApiModelProperty(value = "A list of all coupons of the cart.")
    protected List<Coupon> coupons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<CartEntry> entries) {
        this.entries = entries;
    }
    
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public MoneyValue getNetTotalPrice() {
        return netTotalPrice;
    }

    public void setNetTotalPrice(MoneyValue netTotalPrice) {
        this.netTotalPrice = netTotalPrice;
    }

    public MoneyValue getGrossTotalPrice() {
        return grossTotalPrice;
    }

    public void setGrossTotalPrice(MoneyValue grossTotalPrice) {
        this.grossTotalPrice = grossTotalPrice;
    }
    
    public MoneyValue getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(MoneyValue productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
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

    /**
     * @deprecated
     * @return payment Payment.
     */
    @Deprecated
    public Payment getPayment() {
        return payment;
    }

    /**
     * @deprecated
     * @param payment Payment.
     */
    @Deprecated
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public TaxInfo getTaxInfo() {
        return taxInfo;
    }

    public void setTaxInfo(TaxInfo taxInfo) {
        this.taxInfo = taxInfo;
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
