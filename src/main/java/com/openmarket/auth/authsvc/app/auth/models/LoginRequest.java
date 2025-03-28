package com.openmarket.auth.authsvc.app.auth.models;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Request object for user login")
public class LoginRequest {
    @Schema(description = "User's email address", example = "john.doe@example.com")
    private String emailAddress;

    @Schema(description = "User's password", example = "securePassword123")
    private String password;
}