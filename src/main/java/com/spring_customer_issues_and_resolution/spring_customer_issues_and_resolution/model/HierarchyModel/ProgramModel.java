package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "program")
public class ProgramModel {
    @Id
    private String programId;
    private String name;
    private Boolean isActive;
    private ProgramType programType;
    @DBRef
    private CountryModel country;

    public ProgramModel() {
    }

    public ProgramModel(String programId) {
        this.programId = programId;
    }

    public ProgramModel(String programId, String name, Boolean isActive, ProgramType programType,
            CountryModel country) {
        this.programId = programId;
        this.name = name;
        this.isActive = isActive;
        this.programType = programType;
        this.country = country;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
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

    public ProgramType getProgramType() {
        return programType;
    }

    public void setProgramType(ProgramType programType) {
        this.programType = programType;
    }

    public CountryModel getCountry() {
        return country;
    }

    public void setCountry(CountryModel country) {
        this.country = country;
    }

}
