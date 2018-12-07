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

import com.adobe.commerce.cif.model.customer.Customer;
import io.swagger.v3.oas.annotations.media.Schema;

public class Payment extends ModelWithDates {

    @Schema(description = "The id of the payment.", required = true)
    protected String id;

    @Schema(description = "The customer the payment belongs to. If this is not set the payment belongs to an " +
            "anonymous customer.")
    protected Customer customer;

    @Schema(description = "DEPRECATED. The method for this payment like Card or Cash.", required = true)
    @Deprecated
    protected String method;

    @Schema(description = "The id of the payment method for this payment.", required = true)
    protected String methodId;

    @Schema(description = "The value of the payment.")
    protected MoneyValue value;

    @Schema(description = "The token used to communicate with the payment service provider.")
    protected String token;

    @Schema(description = "The external status code for the payment.")
    protected String statusCode;

    @Schema(description = "The external status message/text for the payment.")
    protected String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @deprecated
     * @return Payment Method
     */
    @Deprecated
    public String getMethod() {
        return method;
    }

    /**
     * @deprecated
     * @param method Payment Method
     */
    @Deprecated
    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public MoneyValue getValue() {
        return value;
    }

    public void setValue(MoneyValue value) {
        this.value = value;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
