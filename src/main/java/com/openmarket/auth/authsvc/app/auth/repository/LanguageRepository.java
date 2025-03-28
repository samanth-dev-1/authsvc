package com.openmarket.auth.authsvc.app.auth.repository;

import com.openmarket.auth.authsvc.app.auth.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
}