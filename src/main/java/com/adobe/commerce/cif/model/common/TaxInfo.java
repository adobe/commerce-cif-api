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

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class TaxInfo {

    @ApiModelProperty(value = "The value of the tax info, which is the total value of the tax portions.", required = true)
    protected MoneyValue value;

    @ApiModelProperty(value = "The portions for this tax.")
    protected List<TaxPortion> portions;

    public MoneyValue getValue() {
        return value;
    }

    public void setValue(MoneyValue value) {
        this.value = value;
    }

    public List<TaxPortion> getPortions() {
        return portions;
    }

    public void setPortions(List<TaxPortion> portions) {
        this.portions = portions;
    }
}
