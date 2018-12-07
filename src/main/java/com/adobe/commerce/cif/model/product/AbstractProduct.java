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

import com.adobe.commerce.cif.model.category.Category;
import com.adobe.commerce.cif.model.common.Asset;
import com.adobe.commerce.cif.model.common.Attribute;
import com.adobe.commerce.cif.model.common.ModelWithDates;
import com.adobe.commerce.cif.model.common.MoneyValue;
import io.swagger.v3.oas.annotations.media.Schema;

public abstract class AbstractProduct extends ModelWithDates {

    @Schema(description = "The internal unique ID of the product in the commerce backend system.", required = true)
    protected String id;
    
    @Schema(description = "The name of the product.", required = true)
    protected String name;

    @Schema(description = "Slug or human readable key that uniquely identifies the product and that can be used for SEO friendly urls. The slug can be a path containing slashes.")
    protected String slug;

    @Schema(description = "The description of the product.")
    protected String description;

    @Schema(description = "The prices for this product.", required = true)
    protected List<MoneyValue> prices;

    @Schema(description = "The categories for this product.")
    protected List<Category> categories;

    @Schema(description = "The assets for this product.")
    protected List<Asset> assets;

    @Schema(description = "The attributes for this product.")
    protected List<Attribute> attributes;

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<MoneyValue> getPrices() {
        return prices;
    }

    public void setPrices(List<MoneyValue> prices) {
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
}
