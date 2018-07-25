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

package com.adobe.commerce.cif.model.common;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

public class TaxPortion {

    @ApiModelProperty(value = "The name for this tax portion.", required = true)
    protected String name;

    @ApiModelProperty(value = "The amount in cents for the tax portion.", required = true)
    protected BigDecimal centAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCentAmount() {
        return centAmount;
    }

    public void setCentAmount(BigDecimal centAmount) {
        this.centAmount = centAmount;
    }
}


