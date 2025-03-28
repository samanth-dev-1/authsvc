package com.openmarket.auth.authsvc.app.auth.controllers;

import com.openmarket.auth.authsvc.app.auth.models.Language;
import com.openmarket.auth.authsvc.app.auth.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping
    public ResponseEntity<Language> createLanguage(@RequestBody Language language) {
        return ResponseEntity.ok(languageService.createLanguage(language));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguage(@PathVariable Integer id) {
        return languageService.getLanguage(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Language>> getAllLanguages() {
        return ResponseEntity.ok(languageService.getAllLanguages());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable Integer id, @RequestBody Language language) {
        language.setId(id);
        return ResponseEntity.ok(languageService.updateLanguage(language));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Integer id) {
        languageService.deleteLanguage(id);
        return ResponseEntity.noContent().build();
    }
}