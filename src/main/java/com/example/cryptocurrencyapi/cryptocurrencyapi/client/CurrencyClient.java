package com.example.cryptocurrencyapi.cryptocurrencyapi.client;

import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.Currency;
import com.example.cryptocurrencyapi.cryptocurrencyapi.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyClient {

    private final CurrencyRepository currencyRepository;

    public List<Currency> retrieveCurrencies() {
        return currencyRepository.findAll();
    }

}
