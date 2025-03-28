package com.openmarket.auth.authsvc.app.auth.service;

import com.openmarket.auth.authsvc.app.auth.models.LoginRequest;
import com.openmarket.auth.authsvc.app.auth.models.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;

    public LoginResponse authenticate(LoginRequest request) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.getUsername(),
//                            request.getPassword()));
//
//            if (authentication.isAuthenticated()) {
//                String token = jwtService.generateToken(request.getUsername());
//                return LoginResponse.builder()
//                        .token(token)
//                        .build();
//            }
//            throw new RuntimeException("Invalid credentials");
//        } catch (AuthenticationException e) {
//            throw new RuntimeException("Authentication failed: " + e.getMessage());
//        }
        return LoginResponse.builder().token(null).build();
    }

    public boolean validateToken(String bearerToken) {
        String token = extractTokenFromBearer(bearerToken);
        return jwtService.isTokenValid(token);
    }

    private String extractTokenFromBearer(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        throw new IllegalArgumentException("Invalid token format");
    }
}