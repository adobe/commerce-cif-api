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

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class Category {

    @ApiModelProperty(value = "The internal unique ID of the category in the commerce backend system.", required = true)
    protected String id;

    @ApiModelProperty(value = "The name of the category.")
    protected String name;

    @ApiModelProperty(value = "The description of the category.")
    protected String description;
    
    @ApiModelProperty(value = "The id of the main parent category (if this category has multiple parents).")
    protected String mainParentCategoryId;

    @ApiModelProperty(value = "The list of parent categories for this category. Depending on the backend system, the returned items may only have their ids being set.")
    protected List<Category> parentCategories;

    @ApiModelProperty(value = "The list of subcategories for this category. Depending on the backend system, the returned items may only have their ids being set.")
    protected List<Category> subCategories;

    @ApiModelProperty(value = "The date when this product was created.")
    protected Date createdDate;

    @ApiModelProperty(value = "The date when this product was last modified.")
    protected Date lastModifiedDate;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainParentCategoryId() {
        return mainParentCategoryId;
    }

    public void setMainParentCategoryId(String mainParentCategoryId) {
        this.mainParentCategoryId = mainParentCategoryId;
    }

    public List<Category> getParentCategories() {
        return parentCategories;
    }

    public void setParentCategories(List<Category> parentCategories) {
        this.parentCategories = parentCategories;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
