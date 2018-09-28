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

package com.adobe.commerce.cif.model.customer;

import io.swagger.annotations.ApiModelProperty;

public class AuthenticationResponse {
    @ApiModelProperty(value = "The access token string as issued by the commerce backend.", required = true)
    protected String access_token;

    @ApiModelProperty(value = "The type of token this is, typically just the string \"bearer\".", required = true, example = "bearer")
    protected String token_type;

    @ApiModelProperty(value = "The scope the user granted to if supported by commerce backend.")
    protected String scope;

    @ApiModelProperty(value = "If the access token expires, the backend should reply with the duration of time the" +
            " access token is granted for.")
    protected Integer expires_in;

    @ApiModelProperty(value = "If the access token will expire, then it is useful to return a refresh token which" +
            " applications can use to obtain another access token.")
    protected String refresh_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
