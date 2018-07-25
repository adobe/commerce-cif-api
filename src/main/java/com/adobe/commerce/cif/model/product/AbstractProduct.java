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

import java.util.Date;
import java.util.List;

import com.adobe.commerce.cif.model.category.Category;
import com.adobe.commerce.cif.model.common.Asset;
import com.adobe.commerce.cif.model.common.Attribute;
import com.adobe.commerce.cif.model.common.Price;
import io.swagger.annotations.ApiModelProperty;

public abstract class AbstractProduct {

    @ApiModelProperty(value = "The internal unique ID of the product in the commerce backend system.", required = true)
    protected String id;

    @ApiModelProperty(value = "The unique SKU of the product assigned by the vendor or manufacturer.")
    protected String sku;
    
    @ApiModelProperty(value = "The name of the product.")
    protected String name;

    @ApiModelProperty(value = "The description of the product.")
    protected String description;

    @ApiModelProperty(value = "The prices for this product.")
    protected List<Price> prices;

    @ApiModelProperty(value = "The categories for this product.")
    protected List<Category> categories;

    @ApiModelProperty(value = "The assets for this product.")
    protected List<Asset> assets;

    @ApiModelProperty(value = "The attributes for this product.")
    protected List<Attribute> attributes;

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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
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
