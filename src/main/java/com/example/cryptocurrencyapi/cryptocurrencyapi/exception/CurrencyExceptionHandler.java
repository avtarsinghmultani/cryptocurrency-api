package com.example.cryptocurrencyapi.cryptocurrencyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CurrencyExceptionHandler {

    @ExceptionHandler(value = GetCurrencyViewFailureException.class)
    public ResponseEntity<Object> exception(GetCurrencyViewFailureException exception) {
        return new ResponseEntity<>("Failed to retrieve Currency Analysis", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = GetCurrencyFailureException.class)
    public ResponseEntity<Object> exception(GetCurrencyFailureException exception) {
        return new ResponseEntity<>("Failed to retrieve Currencies.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = GenerateCurrencyViewException.class)
    public ResponseEntity<Object> exception(GenerateCurrencyViewException exception) {
        return new ResponseEntity<>("Failed to generate Currency Analysis.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
