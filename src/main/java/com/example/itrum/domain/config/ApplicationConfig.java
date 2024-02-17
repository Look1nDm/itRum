package com.example.itrum.domain.config;

import com.example.itrum.service.QueueManager;
import com.example.itrum.service.WalletConsumer;
import com.example.itrum.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final QueueManager queueManager;
    private final WalletService walletService;

    @Bean
    public String consumerThread(){
        Thread consumerThread = new Thread(new WalletConsumer(queueManager, walletService));
        consumerThread.start();
        return "Консьюмер запущен";
    }
}
