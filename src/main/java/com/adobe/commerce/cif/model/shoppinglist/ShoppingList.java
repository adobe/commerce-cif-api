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

package com.adobe.commerce.cif.model.shoppinglist;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import com.adobe.commerce.cif.model.common.LocalizedString;

public class ShoppingList {

    @ApiModelProperty(value = "The id of the shopping list.", required = true)
    protected String id;

    @ApiModelProperty(value = "The name of the shopping list. Localization is optional here, the default language is en.", required = true)
    protected LocalizedString name;

    @ApiModelProperty(value = "The description of the shopping list. Localization is optional here, the default language is en.")
    protected LocalizedString description;

    @ApiModelProperty(value = "The entries of the shopping list.", required = true)
    protected List<ShoppingListEntry> entries;

    @ApiModelProperty(value = "The customer id that owns this shopping list.")
    protected String customerId;

    @ApiModelProperty(value = "The date when this shopping list was created.")
    protected Date createdDate;

    @ApiModelProperty(value = "The date when this shopping list was last modified.")
    protected Date lastModifiedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalizedString getName() {
        return name;
    }

    public void setName(LocalizedString name) {
        this.name = name;
    }

    public LocalizedString getDescription() {
        return description;
    }

    public void setDescription(LocalizedString description) {
        this.description = description;
    }

    public List<ShoppingListEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ShoppingListEntry> entries) {
        this.entries = entries;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
}
