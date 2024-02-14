package com.example.itrum.service.impl;

import com.example.itrum.service.QueueManager;
import com.example.itrum.web.dto.WalletDto;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

@Component
public class QueueManagerImpl implements QueueManager {

    private final LinkedBlockingQueue<WalletDto> queue;

    public QueueManagerImpl() {
        this.queue = new LinkedBlockingQueue<>();
    }
    @Override
    public void put(WalletDto walletDto) throws InterruptedException {
        queue.put(walletDto);
    }
    @Override
    public WalletDto peek() {
        return queue.peek();
    }
    @Override
    public void remove (WalletDto wallet){
        queue.remove(wallet);
    }
}
