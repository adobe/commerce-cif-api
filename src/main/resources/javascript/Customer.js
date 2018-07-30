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

/**
 * Auto generated code based on Swagger definition.
 * Dot not edit manually. Manual changes will be overridden.
 *
 * @version 0.1.124
 */
class Customer {

    /**
     * Represents a Customer
     * @constructor 
     * @param {string} email
     * @param {string} firstName
     * @param {string} id
     * @param {string} lastName
     */
    constructor(email, firstName, id, lastName) {
        /**
         * The date-time when this object was created. The JSON representation must be in RFC339 / ISO8601 format
         * @type {string}
         */
        this.createdAt = undefined;

        /**
         * The date-time when this object was last modified. The JSON representation must be in RFC339 / ISO8601 format
         * @type {string}
         */
        this.lastModifiedAt = undefined;

        /**
         * The unique id of this customer
         * @type {string}
         */
        this.id = id;

        /**
         * The customer's email address
         * @type {string}
         */
        this.email = email;

        /**
         * The firstname of this customer
         * @type {string}
         */
        this.firstName = firstName;

        /**
         * The lastname of this customer
         * @type {string}
         */
        this.lastName = lastName;

    }
}
module.exports.Customer = Customer;
