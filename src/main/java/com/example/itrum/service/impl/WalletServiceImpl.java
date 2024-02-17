package com.example.itrum.service.impl;

import com.example.itrum.domain.exception.IllegalTypeOperationException;
import com.example.itrum.domain.exception.ImpossibleOperationException;
import com.example.itrum.domain.exception.WalletNotFoundException;
import com.example.itrum.domain.wallet.OperationType;
import com.example.itrum.domain.wallet.Wallet;
import com.example.itrum.repository.WalletRepository;
import com.example.itrum.service.WalletService;
import com.example.itrum.web.dto.WalletDto;
import com.example.itrum.web.mapper.WalletMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    @Override
    @Transactional
    public void executeOperation(WalletDto walletDto) {
        Wallet currentWallet = walletMapper.toEntity(walletDto);
        operate(walletDto.getOperationType(), currentWallet, walletDto.getAmount());
        walletRepository.save(currentWallet);
    }

    private void operate(OperationType operationType, Wallet currentWallet, BigDecimal amount) {
        switch (operationType) {
            case DEPOSIT -> currentWallet.setAmount(currentWallet.getAmount().add(amount));
            case WITHDRAW -> currentWallet.setAmount(currentWallet.getAmount().subtract(amount));
            default ->
                    throw new IllegalTypeOperationException("Введен не корректный тип операции, используйте DEPOSIT/WITHDRAW");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getBalance(UUID walletId) {
        Wallet wallet = walletRepository.findWalletByWalletId(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Кошелек не найден."));
        return wallet.getAmount();
    }

    public void checkOperationPossible(WalletDto walletDto) {
        Wallet wallet = walletRepository.findWalletByWalletId(walletDto.getWalletId())
                .orElseThrow(() -> new WalletNotFoundException("Кошелек не найден."));
        checkWithdrawOperationPossible(wallet.getAmount(), walletDto.getAmount());
    }

    private void checkWithdrawOperationPossible(BigDecimal balance, BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new ImpossibleOperationException("Кажется на Вашем счете недостаточно средств для списания.");
        }
    }
}
