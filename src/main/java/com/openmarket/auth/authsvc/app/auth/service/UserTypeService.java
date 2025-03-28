package com.openmarket.auth.authsvc.app.auth.service;

import com.openmarket.auth.authsvc.app.auth.models.UserType;
import com.openmarket.auth.authsvc.app.auth.repository.UserTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserTypeService {
    private final UserTypeRepository userTypeRepository;

    public UserTypeService(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Transactional
    public UserType createOrGetUserType(UserType userType) {
        // Check for existing user type
        Optional<UserType> existingUserType = userTypeRepository.findDuplicateUserType(userType);
        if (existingUserType.isPresent()) {
            return existingUserType.get();
        }

        // If no duplicate found, create new user type
        return userTypeRepository.save(userType);
    }

    public Optional<UserType> getUserType(Integer id) {
        return userTypeRepository.findById(id);
    }

    public List<UserType> getAllUserTypes() {
        return userTypeRepository.findAll();
    }

    @Transactional
    public UserType updateUserType(UserType userType) {
        // Check if user type exists
        if (!userTypeRepository.existsById(userType.getId())) {
            throw new RuntimeException("User type not found with id: " + userType.getId());
        }

        // Check for duplicates excluding the current user type
        Optional<UserType> duplicate = userTypeRepository.findDuplicateUserType(userType);
        if (duplicate.isPresent() && !duplicate.get().getId().equals(userType.getId())) {
            throw new RuntimeException("User type with same name already exists");
        }

        return userTypeRepository.save(userType);
    }

    public void deleteUserType(Integer id) {
        if (!userTypeRepository.existsById(id)) {
            throw new RuntimeException("User type not found with id: " + id);
        }
        userTypeRepository.deleteById(id);
    }
}