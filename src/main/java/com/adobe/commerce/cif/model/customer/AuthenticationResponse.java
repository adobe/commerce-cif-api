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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class AuthenticationResponse {
    @ApiModelProperty(value = "The access token string as issued by the commerce backend.", required = true)
    @JsonProperty("access_token")
    protected String accessToken;

    @ApiModelProperty(value = "The type of token this is, typically just the string \"bearer\".", required = true, example = "bearer")
    @JsonProperty("token_type")
    protected String tokenType;

    @ApiModelProperty(value = "The scope the user granted to if supported by commerce backend.")
    protected String scope;

    @ApiModelProperty(value = "If the access token expires, the backend specifies a number of seconds after which the " +
        "access token expires, and is no longer valid. Expiration of access tokens is optional.")
    @JsonProperty("expires_in")
    protected Integer expiresIn;

    @ApiModelProperty(value = "The refresh token is used to obtain a new access token after the original access token " +
        "is expired. A refresh token will not be returned in case the access token does not expire or the commerce " +
        "engine does not support it.")
    @JsonProperty("refresh_token")
    protected String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
