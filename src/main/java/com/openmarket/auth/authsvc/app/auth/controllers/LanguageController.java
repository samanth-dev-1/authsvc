package com.openmarket.auth.authsvc.app.auth.controllers;

import com.openmarket.auth.authsvc.app.auth.models.Language;
import com.openmarket.auth.authsvc.app.auth.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
@Tag(name = "Language Management", description = "APIs for managing languages")
@SecurityRequirement(name = "Bearer Authentication")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Operation(summary = "Create language", description = "Creates a new language")
    @ApiResponse(responseCode = "200", description = "Language created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @PostMapping
    public ResponseEntity<Language> createLanguage(@RequestBody Language language) {
        return ResponseEntity.ok(languageService.createLanguage(language));
    }

    @Operation(summary = "Get language by ID", description = "Retrieves a language by its ID")
    @ApiResponse(responseCode = "200", description = "Language found")
    @ApiResponse(responseCode = "404", description = "Language not found")
    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguage(@PathVariable Integer id) {
        return languageService.getLanguage(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all languages", description = "Retrieves all languages in the system")
    @ApiResponse(responseCode = "200", description = "Languages retrieved successfully")
    @GetMapping
    public ResponseEntity<List<Language>> getAllLanguages() {
        return ResponseEntity.ok(languageService.getAllLanguages());
    }

    @Operation(summary = "Update language", description = "Updates an existing language")
    @ApiResponse(responseCode = "200", description = "Language updated successfully")
    @ApiResponse(responseCode = "404", description = "Language not found")
    @PutMapping("/{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable Integer id, @RequestBody Language language) {
        language.setId(id);
        return ResponseEntity.ok(languageService.updateLanguage(language));
    }

    @Operation(summary = "Delete language", description = "Deletes a language from the system")
    @ApiResponse(responseCode = "204", description = "Language deleted successfully")
    @ApiResponse(responseCode = "404", description = "Language not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Integer id) {
        languageService.deleteLanguage(id);
        return ResponseEntity.noContent().build();
    }
}