package com.example.cryptocurrencyapi.cryptocurrencyapi.repository;

import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}
