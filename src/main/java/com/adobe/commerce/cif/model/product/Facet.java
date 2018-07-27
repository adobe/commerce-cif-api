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

package com.adobe.commerce.cif.model.product;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class Facet {

    @ApiModelProperty(value = "The id of the facet.", required = true)
    protected String id;

    @ApiModelProperty(value = "The name of the facet.", required = true)
    protected String name;

    @ApiModelProperty(value = "The number of missed items.")
    protected Integer missed;

    @ApiModelProperty(value = "Indicates if the facet is multi selectable.")
    protected Boolean multiSelect;

    @ApiModelProperty(value = "The type of the facet.", required = true)
    protected String type;

    @ApiModelProperty(value = "List of facetValues calculated for this facet.", required = true)
    protected List<FacetValue> values;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMissed() {
        return missed;
    }

    public void setMissed(Integer missed) {
        this.missed = missed;
    }

    public List<FacetValue> getValues() {
        return values;
    }

    public void setValues(List<FacetValue> values) {
        this.values = values;
    }

    public Boolean getMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(Boolean multiSelect) {
        this.multiSelect = multiSelect;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
