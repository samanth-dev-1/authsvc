package com.openmarket.auth.authsvc.app.auth.models;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String mobileNumber;
    private String password;
    private Address address;
    private Integer userTypeId;
    private Integer defaultLanguageId;
}