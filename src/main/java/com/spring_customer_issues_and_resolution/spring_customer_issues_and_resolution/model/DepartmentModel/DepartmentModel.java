package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.DepartmentModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "department")
public class DepartmentModel {
    @Id
    private String departmentId;
    private String name;
    private List<String> email = new ArrayList<>();

    public DepartmentModel() {
    }

    public DepartmentModel(String departmentId) {
        this.departmentId = departmentId;
    }

    public DepartmentModel(String departmentId, String name, List<String> email) {
        this.departmentId = departmentId;
        this.name = name;
        this.email = email;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

}
