package com.openmarket.auth.authsvc.app.auth.service;

import com.openmarket.auth.authsvc.app.auth.models.Language;
import com.openmarket.auth.authsvc.app.auth.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Language createLanguage(Language language) {
        return languageRepository.save(language);
    }

    public Optional<Language> getLanguage(Integer id) {
        return languageRepository.findById(id);
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public Language updateLanguage(Language language) {
        return languageRepository.save(language);
    }

    public void deleteLanguage(Integer id) {
        languageRepository.deleteById(id);
    }
}