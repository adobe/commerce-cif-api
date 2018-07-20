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

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class InventoryItem {

    @ApiModelProperty(value = "The inventory identifier.", required = true)
    protected String inventoryId;

    @ApiModelProperty(value = "The product identifier.", required = true)
    protected String productId;

    @ApiModelProperty(value = "The scope for the inventory (i.e store or channel).")
    protected String scope;

    @ApiModelProperty(value = "The product available quantity for this inventory.", required = true)
    protected Integer availableQuantity;

    @ApiModelProperty(value = "The period in days when this inventory is restocked.")
    protected Integer restockDaysPeriod;

    @ApiModelProperty(value = "The next expected delivery date for this inventory.")
    protected Date expectedDeliveryDate;

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
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
