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

package com.adobe.commerce.cif.model.common;

import java.util.HashMap;

import io.swagger.annotations.ApiModel;

@SuppressWarnings("serial")
@ApiModel
public class LocalizedString extends HashMap<String, String> {
    
    // We keep this simple class to prevent using Map<String, String> directly in other classes
    // and to be able to add documentation and examples in the Swagger file
    // See https://stackoverflow.com/questions/28644595/how-to-define-a-map-in-swagger
}
