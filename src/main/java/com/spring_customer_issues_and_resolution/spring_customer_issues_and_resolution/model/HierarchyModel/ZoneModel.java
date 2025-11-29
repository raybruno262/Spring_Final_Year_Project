package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "zone")
public class ZoneModel {
    @Id
    private String zoneId;
    private String name;
    private Boolean isActive;
    @DBRef
    private SubRegionModel subRegion;

    public ZoneModel() {
    }

    public ZoneModel(String zoneId) {
        this.zoneId = zoneId;
    }

    public ZoneModel(String zoneId, String name, Boolean isActive, SubRegionModel subRegion) {
        this.zoneId = zoneId;
        this.name = name;
        this.isActive = isActive;
        this.subRegion = subRegion;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
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

    public SubRegionModel getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(SubRegionModel subRegion) {
        this.subRegion = subRegion;
    }

}
