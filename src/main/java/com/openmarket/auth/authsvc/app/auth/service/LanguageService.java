package com.openmarket.auth.authsvc.app.auth.service;

import com.openmarket.auth.authsvc.app.auth.models.Language;
import com.openmarket.auth.authsvc.app.auth.repository.LanguageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Transactional
    public Language createOrGetLanguage(Language language) {
        // Check for existing language
        Optional<Language> existingLanguage = languageRepository.findDuplicateLanguage(language);
        if (existingLanguage.isPresent()) {
            return existingLanguage.get();
        }

        // If no duplicate found, create new language
        return languageRepository.save(language);
    }

    public Optional<Language> getLanguage(Integer id) {
        return languageRepository.findById(id);
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    @Transactional
    public Language updateLanguage(Language language) {
        // Check if language exists
        if (!languageRepository.existsById(language.getId())) {
            throw new RuntimeException("Language not found with id: " + language.getId());
        }

        // Check for duplicates excluding the current language
        Optional<Language> duplicate = languageRepository.findDuplicateLanguage(language);
        if (duplicate.isPresent() && !duplicate.get().getId().equals(language.getId())) {
            throw new RuntimeException("Language with same name and code already exists");
        }

        return languageRepository.save(language);
    }

    public void deleteLanguage(Integer id) {
        if (!languageRepository.existsById(id)) {
            throw new RuntimeException("Language not found with id: " + id);
        }
        languageRepository.deleteById(id);
    }
}