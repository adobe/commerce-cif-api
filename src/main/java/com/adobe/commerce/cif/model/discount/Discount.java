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

package com.adobe.commerce.cif.model.discount;

import com.adobe.commerce.cif.model.common.MoneyValue;
import io.swagger.annotations.ApiModelProperty;

public class Discount {

    @ApiModelProperty(value = "The id for the discount.", required = true)
    protected String id;

    @ApiModelProperty(value = "The type of the discount.", required = true)
    protected String type;

    @ApiModelProperty(value = "The name of the discount.")
    protected String name;

    @ApiModelProperty(value = "The description associated with the discount. May be displayed in the UI.")
    protected String description;

    @ApiModelProperty(value = "The value which is discounted.", required = true)
    protected MoneyValue value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public MoneyValue getValue() {
        return value;
    }

    public void setValue(MoneyValue value) {
        this.value = value;
    }
}
