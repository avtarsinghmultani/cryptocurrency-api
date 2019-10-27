package com.example.cryptocurrencyapi.cryptocurrencyapi.controller;

import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.Currency;
import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.CurrencyView;
import com.example.cryptocurrencyapi.cryptocurrencyapi.exception.GetCurrencyViewFailureException;
import com.example.cryptocurrencyapi.cryptocurrencyapi.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/currency")
@CrossOrigin
@RequiredArgsConstructor
public class CurrencyController {


    private final CurrencyService currencyService;

    @RequestMapping("/all")
    public ResponseEntity<List<Currency>> getAll()
    {
        return new ResponseEntity<>(currencyService.retrieveCurrencies(), HttpStatus.OK);
    }

    @RequestMapping("/views")
    public ResponseEntity<List<CurrencyView>> getAllCurrencyView() {
        return new ResponseEntity<>(currencyService.retrieveCurrencyView(), HttpStatus.OK);
    }
}
