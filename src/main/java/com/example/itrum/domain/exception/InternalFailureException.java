package com.example.itrum.domain.exception;

public class InternalFailureException extends RuntimeException{
    public InternalFailureException(String message) {
        super(message);
    }
}
