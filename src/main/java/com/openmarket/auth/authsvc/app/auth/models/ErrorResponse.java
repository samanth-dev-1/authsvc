package com.openmarket.auth.authsvc.app.auth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Error response object")
public class ErrorResponse {
    @Schema(description = "Error message", example = "Email address already exists")
    private String message;

    @Schema(description = "Path where error occurred", example = "/api/users")
    private String path;

    @Schema(description = "Timestamp of the error", example = "2024-03-28T12:30:45.123Z")
    private String timestamp;

    @Schema(description = "Error code", example = "USER_001")
    private String code;

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }

    public ErrorResponse(String message, String path) {
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now().toString();
    }

    public ErrorResponse(String message, String code, String path) {
        this.message = message;
        this.code = code;
        this.path = path;
        this.timestamp = LocalDateTime.now().toString();
    }
}