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
import java.util.List;

public class TaxInfo {

    @ApiModelProperty(value = "The amount in cents for the tax info.", required = true)
    protected BigDecimal totalCentAmount;

    @ApiModelProperty(value = "The portions for this tax.")
    protected List<TaxPortion> taxPortions;

    public BigDecimal getTotalCentAmount() {
        return totalCentAmount;
    }

    public void setTotalCentAmount(BigDecimal totalCentAmount) {
        this.totalCentAmount = totalCentAmount;
    }

    public List<TaxPortion> getTaxPortions() {
        return taxPortions;
    }

    public void setTaxPortions(List<TaxPortion> taxPortions) {
        this.taxPortions = taxPortions;
    }
}
