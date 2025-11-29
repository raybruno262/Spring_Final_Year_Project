package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "branch")
public class BranchModel {
    @Id
    private String branchId;
    private String name;
    private Boolean isActive;
    @DBRef
    private ProgramModel program;

    public BranchModel() {
    }

    public BranchModel(String branchId) {
        this.branchId = branchId;
    }

    public BranchModel(String branchId, String name, Boolean isActive, ProgramModel program) {
        this.branchId = branchId;
        this.name = name;
        this.isActive = isActive;
        this.program = program;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public ProgramModel getProgram() {
        return program;
    }

    public void setProgram(ProgramModel program) {
        this.program = program;
    }

}
