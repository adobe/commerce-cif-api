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

import com.adobe.commerce.cif.model.common.Price;
import com.adobe.commerce.cif.model.common.TaxInfo;
import io.swagger.annotations.ApiModelProperty;

public class ShippingInfo {
    
    @ApiModelProperty(value = "The shipping method id.", required = true)
    protected String id;

    @ApiModelProperty(value = "The shipping method name.", required = true)
    protected String name;
    
    @ApiModelProperty(value = "The shipping price.", required = true)
    protected Price price;
    
    @ApiModelProperty(value = "The discounted shipping price.")
    protected Price discountedPrice;

    @ApiModelProperty(value = "The tax for the shipping.", required = true)
    protected TaxInfo taxInfo;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Price getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Price discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TaxInfo getTaxInfo() {
        return taxInfo;
    }

    public void setTaxInfo(TaxInfo taxInfo) {
        this.taxInfo = taxInfo;
    }
}
