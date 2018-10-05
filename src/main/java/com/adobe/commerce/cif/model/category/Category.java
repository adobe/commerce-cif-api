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

package com.adobe.commerce.cif.model.category;

import java.util.List;

import com.adobe.commerce.cif.model.common.ModelWithDates;
import io.swagger.annotations.ApiModelProperty;

public class Category extends ModelWithDates {

    @ApiModelProperty(value = "The internal unique ID of the category in the commerce backend system.", required = true)
    protected String id;

    @ApiModelProperty(value = "The name of the category.")
    protected String name;

    @ApiModelProperty(value = "Slug or human readable key that uniquely identifies the category and that can be used for SEO friendly urls. The slug can be a path containing slashes.")
    protected String slug;

    @ApiModelProperty(value = "The description of the category.")
    protected String description;
    
    @ApiModelProperty(value = "The id of the main parent category (if this category has multiple parents).")
    protected String mainParentId;

    @ApiModelProperty(value = "The list of parent categories for this category. Depending on the backend system, the returned items may only have their ids being set.")
    protected List<Category> parents;

    @ApiModelProperty(value = "The list of subcategories for this category. Depending on the backend system, the returned items may only have their ids being set.")
    protected List<Category> children;

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainParentId() {
        return mainParentId;
    }

    public void setMainParentId(String mainParentId) {
        this.mainParentId = mainParentId;
    }

    public List<Category> getParents() {
        return parents;
    }

    public void setParents(List<Category> parents) {
        this.parents = parents;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }
}
