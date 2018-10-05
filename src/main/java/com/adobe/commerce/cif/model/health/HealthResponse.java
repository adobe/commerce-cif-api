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

package com.adobe.commerce.cif.model.health;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class HealthResponse<T> {

    @ApiModelProperty(value = "The list of status reports for this response.")
    protected List<T> reports;

    public List<T> getReports() {
        return reports;
    }

    public void setReports(List<T> reports) {
        this.reports = reports;
    }
}
