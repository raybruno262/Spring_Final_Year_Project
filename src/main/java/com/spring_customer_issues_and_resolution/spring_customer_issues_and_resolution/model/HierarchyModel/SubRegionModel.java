package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subregion")
public class SubRegionModel {
    @Id
    private String subRegionId;
    private String name;
    private Boolean isActive;
    @DBRef
    private RegionModel region;

    public SubRegionModel() {
    }

    public SubRegionModel(String subRegionId) {
        this.subRegionId = subRegionId;
    }

    public SubRegionModel(String subRegionId, String name, Boolean isActive, RegionModel region) {
        this.subRegionId = subRegionId;
        this.name = name;
        this.isActive = isActive;
        this.region = region;
    }

    public String getSubRegionId() {
        return subRegionId;
    }

    public void setSubRegionId(String subRegionId) {
        this.subRegionId = subRegionId;
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

    public RegionModel getRegion() {
        return region;
    }

    public void setRegion(RegionModel region) {
        this.region = region;
    }

}
