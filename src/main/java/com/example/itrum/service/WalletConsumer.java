package com.example.itrum.service;

import com.example.itrum.domain.exception.InternalFailureException;
import com.example.itrum.web.dto.WalletDto;

public class WalletConsumer implements Runnable{
    private final QueueManager queueManager;
    private final WalletService walletService;
    private boolean running;

    public WalletConsumer(QueueManager queueManager, WalletService walletService) {
        this.queueManager = queueManager;
        this.walletService = walletService;
        running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                WalletDto wallet = queueManager.peek();
                if (wallet != null) {
                    walletService.executeOperation(wallet);
                    queueManager.remove(wallet);
                }
            } catch (Exception e) {
                throw new InternalFailureException("Проблема на стороне сервера");
            }
        }
    }
}
