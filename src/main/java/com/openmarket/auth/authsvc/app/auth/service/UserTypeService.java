package com.openmarket.auth.authsvc.app.auth.service;

import com.openmarket.auth.authsvc.app.auth.models.UserType;
import com.openmarket.auth.authsvc.app.auth.repository.UserTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTypeService {
    private final UserTypeRepository userTypeRepository;

    public UserTypeService(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public UserType createUserType(UserType userType) {
        return userTypeRepository.save(userType);
    }

    public Optional<UserType> getUserType(Integer id) {
        return userTypeRepository.findById(id);
    }

    public List<UserType> getAllUserTypes() {
        return userTypeRepository.findAll();
    }

    public UserType updateUserType(UserType userType) {
        return userTypeRepository.save(userType);
    }

    public void deleteUserType(Integer id) {
        userTypeRepository.deleteById(id);
    }
}