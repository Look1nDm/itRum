package com.example.itrum.web.controller;

import com.example.itrum.domain.exception.ExceptionBody;
import com.example.itrum.domain.exception.IllegalTypeOperationException;
import com.example.itrum.domain.exception.ImpossibleOperationException;
import com.example.itrum.domain.exception.WalletNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(IllegalTypeOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handlerTypeMapping(final IllegalTypeOperationException e) {
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(WalletNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceMapping(final WalletNotFoundException e) {
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(ImpossibleOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleMethodArgumentNotValid(final ImpossibleOperationException e) {
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(EnumConstantNotPresentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handlerEnumConstantNotPresent(final EnumConstantNotPresentException e) {
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handlerException(final Exception e) {
        return new ExceptionBody(e.getMessage());
    }
}
