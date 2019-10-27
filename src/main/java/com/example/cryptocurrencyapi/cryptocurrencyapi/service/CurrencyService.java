package com.example.cryptocurrencyapi.cryptocurrencyapi.service;

import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.Currency;
import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.CurrencyView;

import java.util.List;

public interface CurrencyService {

    public List<Currency> retrieveCurrencies();

    public List<CurrencyView> retrieveCurrencyView();
}
