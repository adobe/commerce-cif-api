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


'use strict';

const request = require('request');

// This should point to some commerce backend URL to GET products
const url = 'https://my-commerce-backend/products/';

let sampleProductData = {
    pid: '123',
    name: 'A simple name',
    price: {
        currencyCode: 'USD',
        value: 10
    }
}

function main(args) {

    let httpClient = new HttpClient();
    
    return new Promise((resolve, reject) => {
        request(url + args.id, function (error, response, body) {
            if(response.statusCode == 200){
                return resolve(buildResponse(body));
            } else {
                return resolve(buildResponse(sampleProductData));
            }
          });
    });
}

function buildResponse(backendProduct) {
    return {
        statusCode: 200,
        headers: { 'Content-Type': 'application/json' },
        body: mapProduct(backendProduct)
    };
}

/**
 * Example conversion of a commerce backend product into a CIF product
 * 
 * @param backendProduct The JSON product data coming from the commerce system backend.
 * @returns a CIF product.
 */
function mapProduct(backendProduct) {
    return {
        id: backendProduct.pid,
        name: {
            en: backendProduct.name
        },
        prices: [
            {
                currency: backendProduct.price.currencyCode,
                centAmount: backendProduct.price.value * 100
            }
        ]
    };
}

module.exports.main = main;
