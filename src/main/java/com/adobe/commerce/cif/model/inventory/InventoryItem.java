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

package com.adobe.commerce.cif.model.inventory;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

public class InventoryItem {

    @Schema(description = "The inventory identifier.", required = true)
    protected String id;

    @Schema(description = "The product identifier.", required = true)
    protected String productId;

    @Schema(description = "The scope for the inventory (i.e store or channel).")
    protected String scope;

    @Schema(description = "The product available quantity for this inventory.", required = true)
    protected Integer availableQuantity;

    @Schema(description = "The period in days when this inventory is restocked.")
    protected Integer restockDaysPeriod;

    @Schema(description = "The next expected delivery date for this inventory.")
    protected Date expectedDeliveryDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getRestockDaysPeriod() {
        return restockDaysPeriod;
    }

    public void setRestockDaysPeriod(Integer restockDaysPeriod) {
        this.restockDaysPeriod = restockDaysPeriod;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }
}
