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

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.adobe.commerce.cif.model.graphql.GraphqlRequest;
import com.adobe.commerce.cif.model.graphql.GraphqlResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.adobe.commerce.cif.api.Constants.ACCEPT_LANGUAGE;
import static com.adobe.commerce.cif.api.Constants.ACCEPT_LANGUAGE_DESC;

@Path("/graphql")
@Tag(name = "graphql")
@Produces(MediaType.APPLICATION_JSON)
public interface GraphqlApi {

    @POST
    @Path("/")
    @Operation(summary = "Processes a GraphQL request and returns a GraphQL response.")
    @Consumes(MediaType.APPLICATION_JSON)
    GraphqlResponse postRequest(
        @Parameter(description = "The graphQL JSON request", required = true)
        GraphqlRequest request,

        @Parameter(description = ACCEPT_LANGUAGE_DESC)
        @HeaderParam(ACCEPT_LANGUAGE) String acceptLanguage
    );
}
