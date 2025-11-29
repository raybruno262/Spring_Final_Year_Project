package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.UserModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserModel {
    @Id
    private String userId;
    private String names;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String jobTitle;
    @DBRef
    private UserModel supervisor;
    private byte[] image;
    private Boolean isActive;

}
