package com.example.itrum.domain.exception;

public class WalletNotFoundException extends RuntimeException{
    public WalletNotFoundException(String message) {
        super(message);
    }
}
