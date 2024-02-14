package com.example.itrum.service;

import com.example.itrum.domain.wallet.OperationType;
import com.example.itrum.domain.wallet.Wallet;
import com.example.itrum.web.dto.WalletDto;

import java.math.BigDecimal;
import java.util.UUID;

public interface WalletService {

    void executeOperation(WalletDto wallet);
    BigDecimal getBalance(UUID walletId);
    void checkOperationPossible(WalletDto walletDto);

}
