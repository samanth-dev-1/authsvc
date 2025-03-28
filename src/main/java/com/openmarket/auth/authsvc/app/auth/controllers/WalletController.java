package com.openmarket.auth.authsvc.app.auth.controllers;

import com.openmarket.auth.authsvc.app.auth.models.Wallet;
import com.openmarket.auth.authsvc.app.auth.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/wallets")
@Tag(name = "Wallet Management", description = "APIs for managing wallets")
@SecurityRequirement(name = "Bearer Authentication")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @Operation(summary = "Create a new wallet")
    @ApiResponse(responseCode = "200", description = "Wallet created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @PostMapping
    public ResponseEntity<Wallet> createWallet() {
        return ResponseEntity.ok(walletService.createWallet());
    }

    @Operation(summary = "Get wallet by ID")
    @ApiResponse(responseCode = "200", description = "Wallet found")
    @ApiResponse(responseCode = "404", description = "Wallet not found")
    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWallet(@PathVariable UUID id) {
        return walletService.getWallet(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all wallets")
    @ApiResponse(responseCode = "200", description = "Wallets retrieved successfully")
    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }

    @Operation(summary = "Update wallet")
    @ApiResponse(responseCode = "200", description = "Wallet updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "404", description = "Wallet not found")
    @PutMapping
    public ResponseEntity<Wallet> updateWallet(@RequestBody Wallet wallet) {
        return ResponseEntity.ok(walletService.updateWallet(wallet));
    }
}