package com.openmarket.auth.authsvc.app.auth.service;

import com.openmarket.auth.authsvc.app.auth.models.*;
import com.openmarket.auth.authsvc.app.auth.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AddressService addressService;
    private final WalletService walletService;
    private final UserTypeService userTypeService;
    private final LanguageService languageService;

    public UserService(UserRepository userRepository,
            AddressService addressService,
            WalletService walletService,
            UserTypeService userTypeService,
            LanguageService languageService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
        this.walletService = walletService;
        this.userTypeService = userTypeService;
        this.languageService = languageService;
    }

    @Transactional
    public User createUser(CreateUserRequest request) {
        // Validate unique constraints
        validateUniqueConstraints(request);

        // Create address and get ID
        Address address = addressService.createOrGetAddress(request.getAddress());

        // Create wallet and get ID
        Wallet wallet = walletService.createWallet();

        // Validate userType exists
        validateUserTypeExists(request.getUserTypeId());

        // Validate language exists
        validateLanguageExists(request.getDefaultLanguageId());

        // Create user with IDs
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmailAddress(request.getEmailAddress());
        user.setMobileNumber(request.getMobileNumber());
        user.setPassword(request.getPassword());
        user.setAddressId(address.getId());
        user.setWalletId(wallet.getId());
        user.setUserTypeId(request.getUserTypeId());
        user.setDefaultLanguageId(request.getDefaultLanguageId());

        return userRepository.save(user);
    }

    private void validateUniqueConstraints(CreateUserRequest request) {
        if (userRepository.existsByEmailAddress(request.getEmailAddress())) {
            throw new RuntimeException("Email address already exists");
        }
        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new RuntimeException("Mobile number already exists");
        }
    }

    private void validateUserTypeExists(Integer userTypeId) {
        if (userTypeId == null || !userTypeService.getUserType(userTypeId).isPresent()) {
            throw new RuntimeException("Invalid user type ID: " + userTypeId);
        }
    }

    private void validateLanguageExists(Integer languageId) {
        if (languageId == null || !languageService.getLanguage(languageId).isPresent()) {
            throw new RuntimeException("Invalid language ID: " + languageId);
        }
    }

    public Optional<User> getUser(UUID id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress);
    }

    @Transactional
    public User updateUser(User user) {
        // Validate user exists
        if (!userRepository.existsById(user.getId())) {
            throw new RuntimeException("User not found with id: " + user.getId());
        }

        // Validate email uniqueness if changed
        Optional<User> existingUserWithEmail = userRepository.findByEmailAddress(user.getEmailAddress());
        if (existingUserWithEmail.isPresent() && !existingUserWithEmail.get().getId().equals(user.getId())) {
            throw new RuntimeException("Email address already exists");
        }

        // Validate foreign keys exist
        validateUserTypeExists(user.getUserTypeId());
        validateLanguageExists(user.getDefaultLanguageId());
        validateAddressExists(user.getAddressId());
        validateWalletExists(user.getWalletId());

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    private void validateAddressExists(Integer addressId) {
        if (addressId != null && !addressService.getAddress(addressId).isPresent()) {
            throw new RuntimeException("Invalid address ID: " + addressId);
        }
    }

    private void validateWalletExists(UUID walletId) {
        if (walletId != null && !walletService.getWallet(walletId).isPresent()) {
            throw new RuntimeException("Invalid wallet ID: " + walletId);
        }
    }
}