package com.openmarket.auth.authsvc.app.auth.controllers;

import com.openmarket.auth.authsvc.app.auth.models.Wallet;
import com.openmarket.auth.authsvc.app.auth.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet() {
        return ResponseEntity.ok(walletService.createWallet());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWallet(@PathVariable UUID id) {
        return walletService.getWallet(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }

    @PutMapping
    public ResponseEntity<Wallet> updateWallet(@RequestBody Wallet wallet) {
        return ResponseEntity.ok(walletService.updateWallet(wallet));
    }
}