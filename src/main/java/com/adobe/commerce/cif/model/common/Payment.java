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
import io.swagger.annotations.ApiModelProperty;

public class Payment extends ModelWithDates {

    @ApiModelProperty(value = "The id of the payment.", required = true)
    protected String id;

    @ApiModelProperty(value = "The customer the payment belongs to. If this is not set the payment belongs to an " +
            "anonymous customer.")
    protected Customer customer;

    @ApiModelProperty(value = "The method for this payment like Card or Cash.", required = true)
    protected String method;

    @ApiModelProperty(value = "The value of the payment.")
    protected MoneyValue value;

    @ApiModelProperty(value = "The token used to communicate with the payment service provider.")
    protected String token;

    @ApiModelProperty(value = "The external status code for the payment.")
    protected String statusCode;

    @ApiModelProperty(value = "The external status message/text for the payment.")
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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
