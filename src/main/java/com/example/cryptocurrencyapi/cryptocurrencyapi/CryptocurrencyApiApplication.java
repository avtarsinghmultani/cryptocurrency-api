package com.example.cryptocurrencyapi.cryptocurrencyapi;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
@Log4j2
public class CryptocurrencyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptocurrencyApiApplication.class, args);
		log.info("Application started successfully.");
	}



}
