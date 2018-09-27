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

package com.adobe.commerce.cif.model;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.adobe.commerce.cif.model.cart.Cart;
import com.adobe.commerce.cif.model.cart.Order;
import com.adobe.commerce.cif.model.cart.ShippingInfo;
import com.adobe.commerce.cif.model.cart.ShippingMethod;
import com.adobe.commerce.cif.model.category.Category;
import com.adobe.commerce.cif.model.common.Address;
import com.adobe.commerce.cif.model.common.PagedResponse;
import com.adobe.commerce.cif.model.common.Payment;
import com.adobe.commerce.cif.model.common.PaymentMethod;
import com.adobe.commerce.cif.model.common.TaxInfo;
import com.adobe.commerce.cif.model.customer.LoginResult;
import com.adobe.commerce.cif.model.discount.Discount;
import com.adobe.commerce.cif.model.error.ErrorResponse;
import com.adobe.commerce.cif.model.graphql.GraphqlRequest;
import com.adobe.commerce.cif.model.graphql.GraphqlResponse;
import com.adobe.commerce.cif.model.inventory.InventoryItem;
import com.adobe.commerce.cif.model.product.Product;
import com.adobe.commerce.cif.model.shoppinglist.ShoppingList;
import com.adobe.commerce.cif.model.test.FieldTester;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ModelTest {
    
    private ObjectMapper objectMapper;
    
    @Before
    public void setUp() {
        
        // We strictly check that all the fields in each JSON example exist in the corresponding java model
        // We will also check that all the fields of the java models are properly set
        // --> this ensures that all JSON examples are up-to-date and fully match the java models
        
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }
    
    @Test
    public void testProduct() throws Exception {
        Product product = map("product.json", Product.class);       
        FieldTester fieldTester = FieldTester.builder()
            .doCheckListElements()
            .withIgnoredListElements(Collections.singleton("categories"))
            .withIgnoredFieldNames(Collections.singleton("isVariantAxis"))
            .withRecursiveDepth(2)
            .build();
        fieldTester.assertAllFieldsNotNull(product);
    }
    
    @Test
    public void testCategory() throws Exception {
        Category category = map("category.json", Category.class);
        FieldTester fieldTester = FieldTester.builder().build();
        fieldTester.assertAllFieldsNotNull(category);
    }
    
    @Test
    public void testCart() throws Exception {
        Cart cart = map("cart.json", Cart.class);
        String[] ignoredFields = { "productVariant", "discountedPrice", "type", "billingAddress", "shippingAddress", "shippingInfo",
            "payment" };
        FieldTester fieldTester = FieldTester.builder()
            .doCheckListElements()
            .withIgnoredFieldNames(new HashSet<String>(Arrays.asList(ignoredFields)))
            .withRecursiveDepth(2)
            .build();
        fieldTester.assertAllFieldsNotNull(cart);
    }
    
    @Test
    public void testTaxInfo() throws Exception {
        TaxInfo taxInfo = map("taxInfo.json", TaxInfo.class);
        FieldTester fieldTester = FieldTester.builder()
            .doCheckListElements()
            .withRecursiveDepth(2)
            .build();
        fieldTester.assertAllFieldsNotNull(taxInfo);
    }
    
    @Test
    public void testShippingInfo() throws Exception {
        ShippingInfo shippingInfo = map("shippingInfo.json", ShippingInfo.class);
        String[] ignoredFields = {"discountedCost"};
        FieldTester fieldTester = FieldTester.builder()
            .doCheckListElements()
            .withIgnoredFieldNames(new HashSet<String>(Arrays.asList(ignoredFields)))
            .withRecursiveDepth(2)
            .build();
        fieldTester.assertAllFieldsNotNull(shippingInfo);
    }
    
    @Test
    public void testAddress() throws Exception {
        Address address = map("address.json", Address.class);
        FieldTester fieldTester = FieldTester.builder()
            .doCheckListElements()
            .build();
        fieldTester.assertAllFieldsNotNull(address);
    }
    
    @Test
    public void testPayment() throws Exception {
        Payment payment = map("payment.json", Payment.class);
        FieldTester fieldTester = FieldTester.builder()
            .doCheckListElements()
            .build();
        fieldTester.assertAllFieldsNotNull(payment);
    }
    
    @Test
    public void testLoginResult() throws Exception {
        LoginResult loginresult = map("loginResult.json", LoginResult.class);
        FieldTester fieldTester = FieldTester.builder()
            .withRecursiveDepth(1)
            .build();
        fieldTester.assertAllFieldsNotNull(loginresult);
    }
    
    @Test
    public void testDiscount() throws Exception {
        Discount discount = map("discount.json", Discount.class);
        FieldTester fieldTester = FieldTester.builder().build();
        fieldTester.assertAllFieldsNotNull(discount);
    }
    
    @Test
    public void testShippingMethod() throws Exception {
        ShippingMethod shippingMethod = map("shippingMethod.json", ShippingMethod.class);
        FieldTester fieldTester = FieldTester.builder().build();
        fieldTester.assertAllFieldsNotNull(shippingMethod);
    }

    @Test
    public void testPaymentMethod() throws Exception {
        PaymentMethod paymentMethod = map("paymentMethod.json", PaymentMethod.class);
        FieldTester fieldTester = FieldTester.builder().build();
        fieldTester.assertAllFieldsNotNull(paymentMethod);
    }
    
    @Test
    public void testOrder() throws Exception {
        Order order = map("order.json", Order.class);
        FieldTester fieldTester = FieldTester.builder().build();
        fieldTester.assertAllFieldsNotNull(order);
    }

    @Test
    public void testShoppingList() throws Exception {
        ShoppingList shoppingList = map("shoppingList.json", ShoppingList.class);
        String[] ignoredFields = {"productVariant"};
        FieldTester fieldTester = FieldTester.builder()
            .doCheckListElements()
            .withIgnoredFieldNames(new HashSet<String>(Arrays.asList(ignoredFields)))
            .withRecursiveDepth(2)
            .build();
        fieldTester.assertAllFieldsNotNull(shoppingList);
    }
    
    @Test
    public void testPagedProductResponse() throws Exception {
        PagedResponse<Product> pagedResponse = map("pagedProductResponse.json", new TypeReference<PagedResponse<Product>>() {});
        FieldTester fieldTester = FieldTester.builder().build();
        fieldTester.assertAllFieldsNotNull(pagedResponse);
    }

    @Test
    public void testPagedProductWithFacetsResponse() throws Exception {
        PagedResponse<Product> pagedResponse = map("pagedProductWithFacetsResponse.json", new TypeReference<PagedResponse<Product>>() {});
        String[] ignoredLists = {"results"};
        FieldTester fieldTester = FieldTester.builder()
            .doCheckListElements()
            .withIgnoredListElements(new HashSet<String>(Arrays.asList(ignoredLists)))
            .withIgnoredFieldNames(Collections.singleton("selected"))
            .withRecursiveDepth(3)
            .build();
        fieldTester.assertAllFieldsNotNull(pagedResponse);
    }
    
    @Test
    public void testPagedFlatCategoryResponse() throws Exception {
        PagedResponse<Category> pagedResponse = map("pagedFlatCategoryResponse.json", new TypeReference<PagedResponse<Category>>() {});
        String[] ignoredFields = { "description", "mainParentId", "createdAt", "lastModifiedAt", "facets", "children" };
        FieldTester fieldTester = FieldTester.builder()
            .doCheckListElements()
            .withIgnoredFieldNames(new HashSet<String>(Arrays.asList(ignoredFields)))
            .withRecursiveDepth(1)
            .build();
        fieldTester.assertAllFieldsNotNull(pagedResponse);
    }
    
    @Test
    public void testPagedTreeCategoryResponse() throws Exception {
        PagedResponse<Category> pagedResponse = map("pagedTreeCategoryResponse.json", new TypeReference<PagedResponse<Category>>() {});
        String[] ignoredFields = { "description", "mainParentId", "createdAt", "lastModifiedAt", "facets", "parents" };
        FieldTester fieldTester = FieldTester.builder()
            .doCheckListElements()
            .withIgnoredFieldNames(new HashSet<String>(Arrays.asList(ignoredFields)))
            .withRecursiveDepth(1)
            .build();
        fieldTester.assertAllFieldsNotNull(pagedResponse);
    }

    @Test
    public void testInventoryResponse() throws Exception {
        PagedResponse<InventoryItem> pagedResponse = map("pagedInventoryResponse.json", new TypeReference<PagedResponse<InventoryItem>>() {});
        //there's no facets in the inventory query paged response so this needs to be ignored.
        String[] ignoredFields = {"facets"};
        FieldTester fieldTester = FieldTester.builder()
                .doCheckListElements()
                .withIgnoredFieldNames(new HashSet<String>(Arrays.asList(ignoredFields)))
                .withRecursiveDepth(1)
                .build();
        fieldTester.assertAllFieldsNotNull(pagedResponse);
    }

    @Test
    public void testErrorResponse() throws Exception {
        ErrorResponse errorResponse = map("errorResponse.json", ErrorResponse.class);
        FieldTester fieldTester = FieldTester.builder().build();
        fieldTester.assertAllFieldsNotNull(errorResponse);
    }

    @Test
    public void testGraphqlRequest() throws Exception {
        GraphqlRequest graphqlRequest = map("graphqlRequest.json", GraphqlRequest.class);
        FieldTester fieldTester = FieldTester.builder().build();
        fieldTester.assertAllFieldsNotNull(graphqlRequest);
    }
    
    @Test
    public void testGraphqlResponse() throws Exception {
        GraphqlResponse graphqlResponse = map("graphqlResponse.json", GraphqlResponse.class);
        FieldTester fieldTester = FieldTester.builder().build();
        fieldTester.assertAllFieldsNotNull(graphqlResponse);
    }
    
    private <T> T map(String filename, Class<T> type) throws Exception {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);
        return objectMapper.readValue(is, type);
    }
    
    private <T> T map(String filename, TypeReference<T> typeReference) throws Exception {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);
        return objectMapper.readValue(is, typeReference);
    }
}
