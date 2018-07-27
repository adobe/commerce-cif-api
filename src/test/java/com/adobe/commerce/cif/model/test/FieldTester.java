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

package com.adobe.commerce.cif.model.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.commerce.cif.model.PojoTest;

import static org.junit.Assert.assertNotNull;

public class FieldTester {

    private static final Logger LOGGER = LoggerFactory.getLogger(FieldTester.class);
    
    private boolean checkListElements = false;
    private Set<String> ignoredFieldNames;
    private Set<String> ignoredListElements;
    private int recursiveDepth = 0;
    
    private FieldTester() {}
    
    public static Builder builder() {
        return new Builder();
    }
    
    /**
     * A builder class used to build {@link FieldTester} instances.
     */
    public static class Builder {
        
        private boolean checkListElements;
        private Set<String> ignoredFieldNames;
        private Set<String> ignoredListElements;
        private int recursiveDepth;
        
        /**
         * When set, the field tester will also check the elements of each field implementing the <code>List</code> interface,
         * except for all field names set by {@link #withIgnoredListElements(List)}
         * 
         * @return The Builder itself.
         */
        public Builder doCheckListElements() {
            checkListElements = true;
            return this;
        }
        
        /**
         * Defines a list of fields that should be ignored when checking if a field is null.
         * This list applies to the current object and sub-objects if list and/or child objects are checked.
         * 
         * @param ignoredFieldNames The list of field names.
         * @return The Builder itself.
         */
        public Builder withIgnoredFieldNames(Set<String> ignoredFieldNames) {
            this.ignoredFieldNames = ignoredFieldNames;
            return this;
        }
        
        /**
         * Defines a list of fields that should be ignored when list elements are being checked.
         * 
         * @param ignoredListElements The list of field names.
         * @return The Builder itself.
         */
        public Builder withIgnoredListElements(Set<String> ignoredListElements) {
            this.ignoredListElements = ignoredListElements;
            return this;
        }
        
        /**
         * When set, the field tester will also check field objects up to the specified depth.<br>
         * <br>
         * A depth of 0 specifies that no child object should be checked.<br>
         * A depth of 1 specifies that the direct children of the object should be checked.<br>
         * A depth N greater than 1 specifies that all children up to depth N should be checked.
         * 
         * @param recursiveDepth The recursive depth of field objects that should be checked.
         * @return The Builder itself.
         */
        public Builder withRecursiveDepth(int recursiveDepth) {
            this.recursiveDepth = recursiveDepth;
            return this;
        }
        
        public FieldTester build() {
            FieldTester ft = new FieldTester();
            ft.checkListElements = checkListElements;
            ft.ignoredFieldNames = ignoredFieldNames != null ? ignoredFieldNames : Collections.emptySet();
            ft.ignoredListElements = ignoredListElements != null ? ignoredListElements : Collections.emptySet();
            ft.recursiveDepth = recursiveDepth;
            return ft;
        }
    }
    
    /**
     * Asserts that all the fields of the given object are not null.<br>
     * <br>
     * If the builder <code>doCheckListElements()</code> was called, this method will also check the elements of each field
     * implementing the <code>List</code> interface, except if the field name is contained in the list of ignored fields
     * when/if the builder <code>withIgnoredListElements()</code> was called.<br>
     * <br>
     * If the builder <code>withRecursiveDepth()</code> was called, this method will also check all the children objects
     * of the <code>obj</code> argument up to the specified depth.
     * 
     * @param obj The object that will be checked.
     * 
     * @throws Exception
     */
    public void assertAllFieldsNotNull(Object obj) throws Exception {
        assertAllFieldsNotNull(obj, checkListElements, ignoredFieldNames, ignoredListElements, recursiveDepth);
    }
    
    private void assertAllFieldsNotNull(Object obj, boolean checkListElements, Set<String> ignoredFieldNames, Set<String> ignoredListElements, int depth) throws Exception {
        for (Field field : getAllFields(obj.getClass())) {
            if (field.getName().contains("jacoco")) {
                continue; // ignore jacoco code coverage "instrumented" fields
            }

            if (ignoredFieldNames.contains(field.getName())) {
                LOGGER.debug("Skipping check for field " + obj.getClass().getSimpleName() + ":" + field.getName());
                continue;
            }
            
            field.setAccessible(true);
            Object value = field.get(obj);
            LOGGER.debug("Checking field for " + obj.getClass().getSimpleName() + ":" + field.getName());
            assertNotNull(obj.getClass().getSimpleName() + ":" + field.getName() + " should not be null", value);
            
            if (depth > 0 && isCheckableModelClass(value.getClass())) {
                assertAllFieldsNotNull(value, checkListElements, ignoredFieldNames, ignoredListElements, depth - 1);
            }
            
            if (depth > 0 && checkListElements && (ignoredListElements == null || !ignoredListElements.contains(field.getName()))
                && List.class.isAssignableFrom(value.getClass())) {
                List<?> list = (List<?>) value;
                for (Object o : list) {
                    assertAllFieldsNotNull(o, checkListElements, ignoredFieldNames, ignoredListElements, depth - 1);
                }
            }
        }
    }
    
    private <T> boolean isCheckableModelClass(Class<T> type) {
        return type.getName().startsWith(PojoTest.MODEL_PACKAGE);
    }
    
    public static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<Field>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields;
    }
}
