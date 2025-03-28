package com.openmarket.auth.authsvc.app.auth.repository;

import com.openmarket.auth.authsvc.app.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmailAddress(String emailAddress);

    boolean existsByEmailAddress(String emailAddress);

    boolean existsByMobileNumber(String mobileNumber);
}