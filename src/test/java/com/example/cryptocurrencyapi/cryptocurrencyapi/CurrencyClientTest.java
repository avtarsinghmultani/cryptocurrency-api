package com.example.cryptocurrencyapi.cryptocurrencyapi;

import com.example.cryptocurrencyapi.cryptocurrencyapi.client.CurrencyClient;
import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.Currency;
import com.example.cryptocurrencyapi.cryptocurrencyapi.repository.CurrencyRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyClient.class)
public class CurrencyClientTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyClient currencyClient;

    private List<Currency> currencies;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.currencyClient).build();// Standalone context

        Currency currency1 = Currency.builder()
                .id(new Long(1))
                .date(Date.valueOf("2019-10-22"))
                .name("Test1")
                .build();
        Currency currency2 = Currency.builder()
                .id(new Long(2))
                .date(Date.valueOf("2019-10-22"))
                .name("Test2")
                .build();
        currencies = new ArrayList<>();
        currencies.add(currency1);
        currencies.add(currency2);
    }

    @Before
    public void contextLoads(){
        assertThat(currencyRepository).isNotNull();
    }

    @Test
    public void testRetrieveCurrencies() throws Exception {
        // Mocking service
        when(currencyRepository.findAll()).thenReturn(currencies);
        List<Currency> list = currencyClient.retrieveCurrencies();
        Assert.assertEquals(currencies, list);
    }
}
