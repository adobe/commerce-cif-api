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

import io.swagger.annotations.ApiModelProperty;

public class Address {

    @ApiModelProperty(value = "Address unique identifier.", required = true)
    protected String id;

    @ApiModelProperty(value = "Address title")
    protected String title;

    @ApiModelProperty(value = "Address salutation")
    protected String salutation;

    @ApiModelProperty(value = "First name.", required = true)
    protected String firstName;

    @ApiModelProperty(value = "Last name.", required = true)
    protected String lastName;

    @ApiModelProperty(value = "Email.")
    protected String email;

    @ApiModelProperty(value = "Phone.")
    protected String phone;

    @ApiModelProperty(value = "Mobile.")
    protected String mobile;

    @ApiModelProperty(value = "Fax.")
    protected String fax;

    @ApiModelProperty(value = "Country code as per ISO 3166-1.", required = true)
    protected String country;

    @ApiModelProperty(value = "Region.")
    protected String region;

    @ApiModelProperty(value = "City.", required = true)
    protected String city;

    @ApiModelProperty(value = "Postal code.", required = true)
    protected String postalCode;

    @ApiModelProperty(value = "Organization name. Can be company name.")
    protected String organizationName;

    @ApiModelProperty(value = "Department.")
    protected String department;

    @ApiModelProperty(value = "Street name.", required = true)
    protected String streetName;

    @ApiModelProperty(value = "Street no.")
    protected String streetNumber;

    @ApiModelProperty(value = "Additional details for the street address.")
    protected String additionalStreetInfo;

    @ApiModelProperty(value = "Additional details for the address.")
    protected String additionalAddressInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getAdditionalStreetInfo() {
        return additionalStreetInfo;
    }

    public void setAdditionalStreetInfo(String additionalStreetInfo) {
        this.additionalStreetInfo = additionalStreetInfo;
    }

    public String getAdditionalAddressInfo() {
        return additionalAddressInfo;
    }

    public void setAdditionalAddressInfo(String additionalAddressInfo) {
        this.additionalAddressInfo = additionalAddressInfo;
    }
}
