package com.openmarket.auth.authsvc.app.auth.service;

import com.openmarket.auth.authsvc.app.auth.models.Wallet;
import com.openmarket.auth.authsvc.app.auth.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet() {
        Wallet wallet = new Wallet();
        wallet.setCashAvailale(BigDecimal.ZERO);
        wallet.setCreditAvailable(BigDecimal.ZERO);
        wallet.setCreditApproved(BigDecimal.ZERO);
        wallet.setActive(true); // TODO : update logic to check if we should make this true
        return walletRepository.save(wallet);
    }

    public Optional<Wallet> getWallet(UUID id) {
        return walletRepository.findById(id);
    }

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public Wallet updateWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }
}