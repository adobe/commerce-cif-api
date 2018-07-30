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
class Address {

    /**
     * Represents a Address
     * @constructor 
     * @param {string} city
     * @param {string} country
     * @param {string} firstName
     * @param {string} id
     * @param {string} lastName
     * @param {string} postalCode
     * @param {string} streetName
     */
    constructor(city, country, firstName, id, lastName, postalCode, streetName) {
        /**
         * Address unique identifier.
         * @type {string}
         */
        this.id = id;

        /**
         * Address title
         * @type {string}
         */
        this.title = undefined;

        /**
         * Address salutation
         * @type {string}
         */
        this.salutation = undefined;

        /**
         * First name.
         * @type {string}
         */
        this.firstName = firstName;

        /**
         * Last name.
         * @type {string}
         */
        this.lastName = lastName;

        /**
         * Email.
         * @type {string}
         */
        this.email = undefined;

        /**
         * Phone.
         * @type {string}
         */
        this.phone = undefined;

        /**
         * Mobile.
         * @type {string}
         */
        this.mobile = undefined;

        /**
         * Fax.
         * @type {string}
         */
        this.fax = undefined;

        /**
         * Country code as per ISO 3166-1.
         * @type {string}
         */
        this.country = country;

        /**
         * Region.
         * @type {string}
         */
        this.region = undefined;

        /**
         * City.
         * @type {string}
         */
        this.city = city;

        /**
         * Postal code.
         * @type {string}
         */
        this.postalCode = postalCode;

        /**
         * Organization name. Can be company name.
         * @type {string}
         */
        this.organizationName = undefined;

        /**
         * Department.
         * @type {string}
         */
        this.department = undefined;

        /**
         * Street name.
         * @type {string}
         */
        this.streetName = streetName;

        /**
         * Street no.
         * @type {string}
         */
        this.streetNumber = undefined;

        /**
         * Additional details for the street address.
         * @type {string}
         */
        this.additionalStreetInfo = undefined;

        /**
         * Additional details for the address.
         * @type {string}
         */
        this.additionalAddressInfo = undefined;

    }
}
module.exports.Address = Address;
