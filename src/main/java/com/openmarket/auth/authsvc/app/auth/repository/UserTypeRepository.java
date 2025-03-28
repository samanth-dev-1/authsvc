package com.openmarket.auth.authsvc.app.auth.repository;

import com.openmarket.auth.authsvc.app.auth.models.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
    @Query("SELECT ut FROM UserType ut WHERE " +
            "LOWER(ut.name) = LOWER(:#{#userType.name})")
    Optional<UserType> findDuplicateUserType(UserType userType);
}