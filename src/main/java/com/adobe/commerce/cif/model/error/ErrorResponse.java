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

package com.adobe.commerce.cif.model.error;

import com.adobe.commerce.cif.model.common.LocalizedString;
import io.swagger.annotations.ApiModelProperty;

public class ErrorResponse {

    @ApiModelProperty(value = "The error type.", example = "coupon")
    protected String type;

    @ApiModelProperty(value = "The error reason.", example = "coupon-expired")
    protected String reason;

    @ApiModelProperty(value = "Localized message", example = "{\n" +
            "        \"en\": \"Coupon expired.\",\n" +
            "        \"de\": \"Gutschein abgelaufen.\"\n" +
            "    }")
    protected LocalizedString message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalizedString getMessage() {
        return message;
    }

    public void setMessage(LocalizedString message) {
        this.message = message;
    }
}
