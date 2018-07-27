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

package com.adobe.commerce.cif.model.shoppinglist;

import com.adobe.commerce.cif.model.common.ModelWithDates;
import com.adobe.commerce.cif.model.product.ProductVariant;
import io.swagger.annotations.ApiModelProperty;

public class ShoppingListEntry extends ModelWithDates {

    @ApiModelProperty(value = "The id of the entry.", required = true)
    protected String id;

    @ApiModelProperty(value = "The product variant for the entry.", required = true)
    protected ProductVariant productVariant;

    @ApiModelProperty(value = "The quantity for the entry.", required = true)
    protected Integer quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
