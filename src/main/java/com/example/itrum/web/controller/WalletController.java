package com.example.itrum.web.controller;

import com.example.itrum.domain.wallet.OperationType;
import com.example.itrum.domain.wallet.Wallet;
import com.example.itrum.service.QueueManager;
import com.example.itrum.service.WalletConsumer;
import com.example.itrum.service.WalletService;
import com.example.itrum.web.dto.WalletDto;
import com.example.itrum.web.mapper.WalletMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    private final WalletService walletService;

    private final QueueManager queueManager;

    public WalletController(WalletService walletService, QueueManager queueManager) {
        this.walletService = walletService;
        this.queueManager = queueManager;

    }

    @PostMapping("/wallet")
    public void walletTransactions(@RequestBody WalletDto walletDto) throws InterruptedException {
        walletService.checkOperationPossible(walletDto);
        queueManager.put(walletDto);
    }

    @GetMapping("/wallets/{WALLET_UUID}")
    public BigDecimal getBalance(@PathVariable UUID WALLET_UUID) {
        return walletService.getBalance(WALLET_UUID);
    }
}
