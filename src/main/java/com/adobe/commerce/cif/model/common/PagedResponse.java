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

import com.adobe.commerce.cif.model.product.Facet;
import io.swagger.annotations.ApiModelProperty;

public class PagedResponse<T> {

    @ApiModelProperty(value = "The offset for this response, this is the number of elements skipped, not a page number.", required = true)
    protected Integer offset;

    @ApiModelProperty(value = "The actual number of results returned in results.", required = true)
    protected Integer count;

    @ApiModelProperty(value = "The total number of results matching the query.", required = true)
    protected Integer total;

    @ApiModelProperty(value = "The results for this response.", required = true)
    protected List<T> results;

    @ApiModelProperty(value = "The list of facets for this response.")
    protected List<Facet> facets;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public List<Facet> getFacets() {
        return facets;
    }

    public void setFacets(List<Facet> facets) {
        this.facets = facets;
    }
}
