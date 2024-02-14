package com.example.itrum.web.controller;

import com.example.itrum.domain.wallet.TypeWallet;
import com.example.itrum.domain.wallet.Wallet;
import com.example.itrum.service.WalletService;
import com.example.itrum.web.dto.WalletDto;
import com.example.itrum.web.mapper.WalletMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class WalletController {

    private final WalletMapper walletMapper;
    private final WalletService walletService;

    @PostMapping("/wallet")
    public WalletDto walletTransactions (@RequestBody Wallet wallet){
        return walletMapper.toDto(walletService.operationWallet(wallet));
    }

    @GetMapping("/wallets/{WALLET_UUID}")
    public BigDecimal getWallet (@PathVariable UUID WALLET_UUID){
        return walletService.getBalance(WALLET_UUID);
    }
// тестовый поинт
    @GetMapping("/type{wallet}")
    public TypeWallet typeWallet(@PathVariable UUID wallet){
        return walletService.getType(wallet);
    }
}
