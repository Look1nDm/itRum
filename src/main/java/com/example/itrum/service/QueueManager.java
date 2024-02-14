package com.example.itrum.service;

import com.example.itrum.web.dto.WalletDto;

public interface QueueManager {

    void put(WalletDto walletDto) throws InterruptedException;
    WalletDto peek();
    void remove (WalletDto wallet);
}
