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

import com.adobe.commerce.cif.model.common.MoneyValue;
import com.adobe.commerce.cif.model.common.TaxInfo;
import io.swagger.v3.oas.annotations.media.Schema;

public class ShippingInfo {
    
    @Schema(description = "The shipping method id.", required = true)
    protected String id;

    @Schema(description = "The shipping method name.", required = true)
    protected String name;
    
    @Schema(description = "The cost of the shipping.", required = true)
    protected MoneyValue cost;
    
    @Schema(description = "The discounted shipping cost.")
    protected MoneyValue discountedCost;

    @Schema(description = "The tax for the shipping.", required = true)
    protected TaxInfo taxInfo;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MoneyValue getDiscountedCost() {
        return discountedCost;
    }

    public void setDiscountedCost(MoneyValue discountedCost) {
        this.discountedCost = discountedCost;
    }

    public MoneyValue getCost() {
        return cost;
    }

    public void setCost(MoneyValue cost) {
        this.cost = cost;
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
