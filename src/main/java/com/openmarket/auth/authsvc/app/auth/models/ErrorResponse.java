package com.openmarket.auth.authsvc.app.auth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private String code;
    private LocalDateTime timestamp;
    private String path;

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(String message, String path) {
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(String message, String code, String path) {
        this.message = message;
        this.code = code;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}