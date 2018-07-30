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
class Payment {

    /**
     * Represents a Payment
     * @constructor 
     * @param {string} id
     * @param {string} method
     */
    constructor(id, method) {
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
         * The id of the payment.
         * @type {string}
         */
        this.id = id;

        /**
         * The customer the payment belongs to. If this is not set the payment belongs to an anonymous customer.
         * @type {Customer}
         */
        this.customer = undefined;

        /**
         * The method for this payment like Card or Cash.
         * @type {string}
         */
        this.method = method;

        /**
         * The amount of the payment.
         * @type {Price}
         */
        this.amount = undefined;

        /**
         * The token used to communicate with the payment service provider.
         * @type {string}
         */
        this.token = undefined;

        /**
         * The external status code for the payment.
         * @type {string}
         */
        this.statusCode = undefined;

        /**
         * The external status message/text for the payment.
         * @type {string}
         */
        this.status = undefined;

    }
}
module.exports.Payment = Payment;
