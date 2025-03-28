package com.openmarket.auth.authsvc.app.auth.service;

import com.openmarket.auth.authsvc.app.auth.models.LoginRequest;
import com.openmarket.auth.authsvc.app.auth.models.LoginResponse;
import com.openmarket.auth.authsvc.app.auth.models.User;
import com.openmarket.auth.authsvc.app.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public LoginResponse authenticate(LoginRequest request) {
        // Find user by email
        User user = userRepository.findByEmailAddress(request.getEmailAddress())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Validate password (in real app, use password encoder)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmailAddress());

        return LoginResponse.builder()
                .token(token)
                .build();
    }

    public boolean validateToken(String bearerToken) {
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            return false;
        }
        String token = bearerToken.substring(7);
        return jwtService.isTokenValid(token);
    }

    public String extractEmailFromToken(String bearerToken) {
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid token format");
        }
        String token = bearerToken.substring(7);
        return jwtService.extractUsername(token);
    }
}