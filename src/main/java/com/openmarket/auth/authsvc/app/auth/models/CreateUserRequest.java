package com.openmarket.auth.authsvc.app.auth.models;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Request object for creating a new user")
public class CreateUserRequest {
    @Schema(description = "User's first name", example = "John")
    private String firstName;

    @Schema(description = "User's last name", example = "Doe")
    private String lastName;

    @Schema(description = "User's email address", example = "john.doe@example.com")
    private String emailAddress;

    @Schema(description = "User's mobile number", example = "1234567890")
    private String mobileNumber;

    @Schema(description = "User's password", example = "securePassword123")
    private String password;

    @Schema(description = "User's address details")
    private Address address;

    @Schema(description = "User type ID", example = "1")
    private Integer userTypeId;

    @Schema(description = "Default language ID", example = "1")
    private Integer defaultLanguageId;
}