package com.example.itrum.service.impl;

import com.example.itrum.domain.exception.IllegalTypeOperation;
import com.example.itrum.domain.exception.ImpossibleOperationException;
import com.example.itrum.domain.exception.InternalFailureException;
import com.example.itrum.domain.wallet.TypeWallet;
import com.example.itrum.domain.wallet.Wallet;
import com.example.itrum.repository.WalletRepository;
import com.example.itrum.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.example.itrum.domain.wallet.TypeWallet.DEPOSIT;
import static com.example.itrum.domain.wallet.TypeWallet.WITHDRAW;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    @Transactional
    public Wallet operationWallet(Wallet wallet) {
        Wallet changeableWallet = walletRepository.findWalletByWalletId(wallet.getWalletId());
        TypeWallet typeWallet = wallet.getTypeWallet();
        if (isValidOperation(typeWallet)) {
            if (typeWallet == DEPOSIT) {
                changeableWallet.setAmount(depositWallet(changeableWallet, wallet));
                walletRepository.save(changeableWallet);
                return changeableWallet;
            } else if (isCorrectedOperationWithdraw(changeableWallet.getAmount(), wallet.getAmount())) {
                changeableWallet.setAmount(withdrawWallet(changeableWallet, wallet));
                return walletRepository.save(changeableWallet);
            }
        }
        throw new InternalFailureException("Что-то пошло не так, попробуйте снова.");
    }

    @Override
    public BigDecimal depositWallet(Wallet balance, Wallet amount) {
        return balance.getAmount().add(amount.getAmount());
    }

    @Override
    public BigDecimal withdrawWallet(Wallet balance, Wallet amount) {
        return balance.getAmount().subtract(amount.getAmount());
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getBalance(UUID walletId) {
        return walletRepository.findWalletByWalletId(walletId).getAmount();
    }

    private boolean isValidOperation(TypeWallet typeWallet) {
        if (typeWallet.equals(WITHDRAW) || typeWallet.equals(DEPOSIT)){
            return true;
        }
            throw new IllegalTypeOperation("Укажите корректный тип операции (DEPOSIT/WITHDRAW).");
    }

    private boolean isCorrectedOperationWithdraw(BigDecimal balance, BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            return true;
        } else {
            throw new ImpossibleOperationException("Кажется на Вашем счете недостаточно средств для списания.");
        }
    }

    public TypeWallet getType(UUID walletId) {
        return walletRepository.findWalletByWalletId(walletId).getTypeWallet();
    }
}
