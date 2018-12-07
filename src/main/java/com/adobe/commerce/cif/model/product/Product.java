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

import io.swagger.v3.oas.annotations.media.Schema;

public class Product extends AbstractProduct {

    @Schema(description = "The unique SKU of the product assigned by the vendor or manufacturer.")
    protected String sku;

    @Schema(description = "The id of the master variant", required = true)
    protected String masterVariantId;

    @Schema(description = "The variants for this product.", required = true)
    protected List<ProductVariant> variants;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getMasterVariantId() {
        return masterVariantId;
    }

    public void setMasterVariantId(String masterVariantId) {
        this.masterVariantId = masterVariantId;
    }

    public List<ProductVariant> getVariants() {
        return variants;
    }

    public void setVariants(List<ProductVariant> variants) {
        this.variants = variants;
    }
    
}
