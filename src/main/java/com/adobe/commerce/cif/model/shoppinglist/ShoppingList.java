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

import java.util.List;

import com.adobe.commerce.cif.model.common.ModelWithDates;
import io.swagger.v3.oas.annotations.media.Schema;

public class ShoppingList extends ModelWithDates {

    @Schema(description = "The id of the shopping list.", required = true)
    protected String id;

    @Schema(description = "The name of the shopping list.", required = true)
    protected String name;

    @Schema(description = "The description of the shopping list.")
    protected String description;

    @Schema(description = "The entries of the shopping list.", required = true)
    protected List<ShoppingListEntry> entries;

    @Schema(description = "The customer id that owns this shopping list.")
    protected String customerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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
}
