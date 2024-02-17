package com.example.itrum.web.controller;

import com.example.itrum.domain.wallet.Wallet;
import com.example.itrum.service.QueueManager;
import com.example.itrum.service.WalletService;
import com.example.itrum.web.dto.WalletDto;
import com.example.itrum.web.mapper.WalletMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;
    private final WalletMapper walletMapper;
    private final QueueManager queueManager;

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> walletTransactions(@RequestBody WalletDto walletDto) throws InterruptedException {
        walletService.checkOperationPossible(walletDto);
        queueManager.put(walletDto);
        return new ResponseEntity<>(walletMapper.toEntity(walletDto), HttpStatus.OK);
    }

    @GetMapping("/wallets/{WALLET_UUID}")
    public BigDecimal getBalance(@PathVariable UUID WALLET_UUID) {
        return walletService.getBalance(WALLET_UUID);
    }
}
