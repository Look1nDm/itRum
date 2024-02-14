package com.example.itrum.service;

import com.example.itrum.web.dto.WalletDto;
import org.springframework.stereotype.Component;

@Component
public class WalletConsumer implements Runnable{
    private final QueueManager queueManager;
    private final WalletService walletService;
    private boolean running;

    public WalletConsumer(QueueManager queueManager, WalletService walletService) {
        this.queueManager = queueManager;
        this.walletService = walletService;
        running = true;
//        consume();
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
                throw new RuntimeException(e);
            }
        }
    }
}
