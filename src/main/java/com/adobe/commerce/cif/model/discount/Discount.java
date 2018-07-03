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

import com.adobe.commerce.cif.model.common.LocalizedString;
import com.adobe.commerce.cif.model.common.Price;
import io.swagger.annotations.ApiModelProperty;

public class Discount {

    @ApiModelProperty(value = "The id for the discount", required = true)
    protected String id;

    @ApiModelProperty(value = "The type of the discount", required = true)
    protected String type;

    @ApiModelProperty(value = "The name of the discount")
    protected LocalizedString name;

    @ApiModelProperty(value = "The message associated with the discount. May be displayed in the UI.")
    protected LocalizedString message;

    @ApiModelProperty(value = "The amount which is discounted. Subtract this to obtain new price", required = true)
    protected Price discountedAmount;

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

    public LocalizedString getName() {
        return name;
    }

    public void setName(LocalizedString name) {
        this.name = name;
    }

    public LocalizedString getMessage() {
        return message;
    }

    public void setMessage(LocalizedString message) {
        this.message = message;
    }

    public Price getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(Price discountedAmount) {
        this.discountedAmount = discountedAmount;
    }
}
