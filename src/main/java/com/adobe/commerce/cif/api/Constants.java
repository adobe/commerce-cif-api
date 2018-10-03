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

package com.adobe.commerce.cif.api;

public final class Constants {
    
    public static final int HTTP_OK = 200;
    public static final String HTTP_OK_MESSAGE = "OK.";

    public static final int HTTP_CREATED = 201;
    public static final String HTTP_CREATED_MESSAGE = "Created.";

    public static final int HTTP_NO_CONTENT = 204;
    public static final String HTTP_NO_CONTENT_MESSAGE = "No Content.";

    public static final int HTTP_BAD_REQUEST = 400;
    public static final String HTTP_BAD_REQUEST_MESSAGE = "Bad Request.";

    public static final int HTTP_UNAUTHORIZED = 401;
    public static final String HTTP_UNAUTHORIZED_MESSAGE = "Unauthorized.";

    public static final int HTTP_FORBIDDEN = 403;
    public static final String HTTP_FORBIDDEN_MESSAGE = "Forbidden.";

    public static final int HTTP_NOT_FOUND = 404;
    public static final String HTTP_NOT_FOUND_MESSAGE = "Not Found.";

    public static final int HTTP_SERVICE_UNAVAILABLE = 503;
    public static final String HTTP_SERVICE_UNAVAILABLE_MESSAGE = "Service Unavailable.";

    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String ACCEPT_LANGUAGE_DESC = "The languages the client is able to understand, and which locale variant is preferred.";
}
