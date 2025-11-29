package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "country")
public class CountryModel {
    @Id
    private String countryId;
    private String name;
    private Boolean isActive;
    @DBRef
    private ZoneModel zone;

    public CountryModel() {
    }

    public CountryModel(String countryId) {
        this.countryId = countryId;
    }

    public CountryModel(String countryId, String name, Boolean isActive, ZoneModel zone) {
        this.countryId = countryId;
        this.name = name;
        this.isActive = isActive;
        this.zone = zone;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public ZoneModel getZone() {
        return zone;
    }

    public void setZone(ZoneModel zone) {
        this.zone = zone;
    }

}
