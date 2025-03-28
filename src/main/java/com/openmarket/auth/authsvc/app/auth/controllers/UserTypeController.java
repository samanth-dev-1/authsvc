package com.openmarket.auth.authsvc.app.auth.controllers;

import com.openmarket.auth.authsvc.app.auth.models.UserType;
import com.openmarket.auth.authsvc.app.auth.service.UserTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/user-types")
@Tag(name = "User Type Management", description = "APIs for managing user types")
@SecurityRequirement(name = "Bearer Authentication")
public class UserTypeController {
    private final UserTypeService userTypeService;

    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @Operation(summary = "Create user type", description = "Creates a new user type or returns existing one")
    @ApiResponse(responseCode = "200", description = "User type created or retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @PostMapping
    public ResponseEntity<UserType> createUserType(@RequestBody UserType userType) {
        return ResponseEntity.ok(userTypeService.createOrGetUserType(userType));
    }

    @Operation(summary = "Get user type by ID", description = "Retrieves a user type by its ID")
    @ApiResponse(responseCode = "200", description = "User type found")
    @ApiResponse(responseCode = "404", description = "User type not found")
    @GetMapping("/{id}")
    public ResponseEntity<UserType> getUserType(@PathVariable Integer id) {
        return userTypeService.getUserType(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all user types", description = "Retrieves all user types in the system")
    @ApiResponse(responseCode = "200", description = "User types retrieved successfully")
    @GetMapping
    public ResponseEntity<List<UserType>> getAllUserTypes() {
        return ResponseEntity.ok(userTypeService.getAllUserTypes());
    }

    @Operation(summary = "Update user type", description = "Updates an existing user type")
    @ApiResponse(responseCode = "200", description = "User type updated successfully")
    @ApiResponse(responseCode = "404", description = "User type not found")
    @PutMapping("/{id}")
    public ResponseEntity<UserType> updateUserType(@PathVariable Integer id, @RequestBody UserType userType) {
        userType.setId(id);
        return ResponseEntity.ok(userTypeService.updateUserType(userType));
    }

    @Operation(summary = "Delete user type", description = "Deletes a user type from the system")
    @ApiResponse(responseCode = "204", description = "User type deleted successfully")
    @ApiResponse(responseCode = "404", description = "User type not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserType(@PathVariable Integer id) {
        userTypeService.deleteUserType(id);
        return ResponseEntity.noContent().build();
    }
}