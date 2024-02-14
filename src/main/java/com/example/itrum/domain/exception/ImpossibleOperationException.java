package com.example.itrum.domain.exception;

public class ImpossibleOperationException extends RuntimeException{
    public ImpossibleOperationException(String message) {
        super(message);
    }
}
