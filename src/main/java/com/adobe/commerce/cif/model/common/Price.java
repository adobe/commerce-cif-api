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

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class Price {

    @ApiModelProperty(value = "The currency code for that price.", required = true)
    protected String currency;

    @ApiModelProperty(value = "The amount in cents for that price.", required = true)
    protected BigDecimal centAmount;

    @ApiModelProperty(value = "The country code for that price.")
    protected String country;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getCentAmount() {
        return centAmount;
    }

    public void setCentAmount(BigDecimal centAmount) {
        this.centAmount = centAmount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
