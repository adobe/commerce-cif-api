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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.commerce.cif.model.cart.CartEntryType;
import com.adobe.commerce.cif.model.test.FieldTester;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PojoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PojoTest.class);
    public static final String MODEL_PACKAGE = PojoTest.class.getPackage().getName();
    
    private static class SetterGetter {
        private Method setter;
        private Method getter;
        
        private SetterGetter(Method setter, Method getter) {
            this.setter = setter;
            this.getter = getter;
        }
    }
    
    @Test
    public void testSettersAndGetters() throws Exception {
        Set<ClassInfo> classes = ClassPath.from(this.getClass().getClassLoader()).getTopLevelClassesRecursive(MODEL_PACKAGE);

        classes.stream()
                .map(c -> c.load())
                .filter( clazz -> !(clazz.getSimpleName().contains("Test") || clazz.isEnum() || Modifier.isAbstract(clazz.getModifiers())))
                .forEach(clazz -> {
                    try {
                        LOGGER.debug("Checking setter/getter for " + clazz.getName());
                        testSettersAndGettersFor(clazz);
                    } catch (Exception e) {
                       LOGGER.error(e.getMessage(), e);
                    }
                });
    }

    private void testSettersAndGettersFor(Class<?> clazz) throws Exception {
        
        Set<String> fieldNames = new HashSet<>();
        for (Field field : FieldTester.getAllFields(clazz)) {
            if (field.getName().contains("jacoco")) {
                continue; // ignore jacoco code coverage "instrumented" fields
            }

            String fieldName = clazz.getSimpleName() + ":" + field.getName();
            assertTrue("Field " + fieldName + " should be 'protected'", Modifier.isProtected(field.getModifiers()));
            assertFalse("Field " + fieldName + " should not be a primitive type", field.getType().isPrimitive());
            fieldNames.add(field.getName());
        }

        // We collect all the methods of the class and its parent class if they are in the same package
        Method[] methods = clazz.getDeclaredMethods();
        Class<?> superClass = clazz.getSuperclass();
        while (superClass.getName().startsWith(MODEL_PACKAGE)) {
            ArrayList<Method> list = new ArrayList<>(Arrays.asList(methods));
            list.addAll(Arrays.asList(superClass.getDeclaredMethods()));
            methods = list.toArray(new Method[list.size()]);
            superClass = superClass.getSuperclass();
        }

        // We collect all setters and getters, and check that they are 'public' and have matching names
        Map<String, Method> getters = new HashMap<>();
        Map<String, Method> setters = new HashMap<>();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("set")) {
                setters.put(StringUtils.uncapitalize(methodName.substring(3)), method);
            } else if (methodName.startsWith("get")) {
                getters.put(StringUtils.uncapitalize(methodName.substring(3)), method);
            } else if (methodName.startsWith("is")) {
                getters.put(StringUtils.uncapitalize(methodName.substring(2)), method);
            }
        }

        Map<String, SetterGetter> gettersSetters = new HashMap<>();
        for (String fieldName : fieldNames) {
            Method setter = setters.get(fieldName);
            Method getter = getters.get(fieldName);

            assertNotNull(clazz.getSimpleName() + " should have a setter for " + fieldName, setter);
            assertNotNull(clazz.getSimpleName() + " should have a getter for " + fieldName, getter);
            assertTrue("Model method " + setter.getName() + " should be 'public'", Modifier.isPublic(setter.getModifiers()));
            assertTrue("Model method " + getter.getName() + " should be 'public'", Modifier.isPublic(getter.getModifiers()));

            SetterGetter setterGetter = new SetterGetter(setter, getter);
            gettersSetters.put(fieldName, setterGetter);
        }
        
        // We check that each setter/getter pair sets and properly gets the same object
        // We use object equality because this fails if a getter or setter uses a primitive instead of the primitive object type
        Object instance = clazz.newInstance();
        for (Map.Entry<String, SetterGetter> entry : gettersSetters.entrySet()) {
            LOGGER.debug("Checking getter/setter for " + clazz.getSimpleName() + ":" + entry.getKey());
            Method setter = entry.getValue().setter;
            Method getter = entry.getValue().getter;
            
            Class<?> type = setter.getParameterTypes()[0];
            Object arg = instantiateArg(type);
            setter.invoke(instance, arg);
            Object res = getter.invoke(instance);
            assertTrue("Setter/getter value for " + clazz.getSimpleName() + ":" + entry.getKey() + " are not equal", arg == res);
        }
    }
    
    /**
     * Returns an instance of the given class.
     * This method properly handles classes that do not have a zero-argument constructor.
     * 
     * @param type The Class to be instantiated.
     * @return An instance of the given class.
     * 
     * @throws Exception
     */
    private Object instantiateArg(Class<?> type) throws Exception {
        if (List.class == type) {
            return new ArrayList<>();
        } else if (boolean.class == type) {
            return true;
        } else if (Boolean.class == type) {
            return Boolean.TRUE;
        } else if (int.class == type) {
            return 0;
        } else if (Integer.class == type) {
            return Integer.valueOf(0);
        } else if (BigDecimal.class == type) {
            return BigDecimal.ONE;
        } else if (CartEntryType.class == type) {
            return CartEntryType.REGULAR;
        } else {
            return type.newInstance();
        }
    }
}
