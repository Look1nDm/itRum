package com.example.itrum.service;

import com.example.itrum.domain.wallet.TypeWallet;
import com.example.itrum.domain.wallet.Wallet;

import java.math.BigDecimal;
import java.util.UUID;

public interface WalletService {

    Wallet operationWallet(Wallet wallet);
    BigDecimal depositWallet(Wallet balance, Wallet amount);

    BigDecimal withdrawWallet(Wallet balance, Wallet amount);

    BigDecimal getBalance(UUID walletId);

    TypeWallet getType(UUID wallet);
}
