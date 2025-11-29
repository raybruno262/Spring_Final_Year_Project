package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "region")
public class RegionModel {
    @Id
    private String regionId;
    private String name;
    private Boolean isActive;

    public RegionModel() {
    }

    public RegionModel(String regionId) {
        this.regionId = regionId;
    }

    public RegionModel(String regionId, String name, Boolean isActive) {
        this.regionId = regionId;
        this.name = name;
        this.isActive = isActive;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
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

}
