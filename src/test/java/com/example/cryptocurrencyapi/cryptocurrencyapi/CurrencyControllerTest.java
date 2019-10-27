package com.example.cryptocurrencyapi.cryptocurrencyapi;


import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import com.example.cryptocurrencyapi.cryptocurrencyapi.controller.CurrencyController;
import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.Currency;
import com.example.cryptocurrencyapi.cryptocurrencyapi.service.CurrencyService;
import org.junit.Before;
import org.junit.BeforeClass;
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

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService currencyService;

    @Autowired
    private CurrencyController currencyController;

    private List<Currency> currencies;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.currencyController).build();// Standalone context

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
        assertThat(currencyService).isNotNull();
    }

    @Test
    public void testGetCurrencies() throws Exception {
        // Mocking service
        when(currencyService.retrieveCurrencies()).thenReturn(currencies);
        mockMvc.perform(get("/currency/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Test1")))
                .andExpect(jsonPath("$[1].name", is("Test2")));
    }

    @Test
    public void getAllCurrenciesCheck() throws Exception
    {
        mockMvc.perform(get("/currency/all")).andExpect(status().isOk());
    }

    @Test
    public void getAllCurrenciesViewsCheck() throws Exception
    {
        mockMvc.perform(get("/currency/views")).andExpect(status().isOk());
    }
}

