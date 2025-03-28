package com.openmarket.auth.authsvc.app.auth.repository;

import com.openmarket.auth.authsvc.app.auth.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    @Query("SELECT l FROM Language l WHERE " +
            "LOWER(l.name) = LOWER(:#{#language.name}) AND " +
            "LOWER(l.region) = LOWER(:#{#language.region})")
    Optional<Language> findDuplicateLanguage(Language language);
}