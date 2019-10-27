package com.example.cryptocurrencyapi.cryptocurrencyapi;

import com.example.cryptocurrencyapi.cryptocurrencyapi.client.CurrencyClient;
import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.Currency;
import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.CurrencyView;
import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.Quote;
import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.QuoteView;
import com.example.cryptocurrencyapi.cryptocurrencyapi.service.CurrencyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyService.class)
public class CurrencyServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyClient currencyClient;

    @Autowired
    private CurrencyService currencyService;

    private List<Currency> currencies1;
    private List<Currency> currencies2;
    private List<CurrencyView> currencyViews1;
    private List<CurrencyView> currencyViews2;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.currencyService).build();// Standalone context
        Quote quote1 = Quote.builder().id(new Long(1)).time(Time.valueOf("09:15:00")).price(BigDecimal.valueOf(10.15)).build();
        Quote quote2 = Quote.builder().id(new Long(2)).time(Time.valueOf("10:15:00")).price(BigDecimal.valueOf(13.15)).build();
        Quote quote3 = Quote.builder().id(new Long(3)).time(Time.valueOf("12:30:00")).price(BigDecimal.valueOf(18.26)).build();
        Quote quote4 = Quote.builder().id(new Long(4)).time(Time.valueOf("14:30:00")).price(BigDecimal.valueOf(16.25)).build();

        QuoteView quoteView1 = QuoteView.builder().id(new Long(1)).time(Time.valueOf("09:15:00")).price(BigDecimal.valueOf(10.15)).build();
        QuoteView quoteView3 = QuoteView.builder().id(new Long(3)).time(Time.valueOf("12:30:00")).price(BigDecimal.valueOf(18.26)).build();

        Currency currency1 = Currency.builder().id(new Long(1)).date(Date.valueOf("2019-10-22")).name("Test1")
                .quotes(new HashSet<Quote>(Arrays.asList(quote1, quote2, quote3, quote4))).build();
        Currency currency2 = Currency.builder().id(new Long(2)).date(Date.valueOf("2019-10-22")).name("Test2")
                .quotes(new HashSet<>(Arrays.asList(quote1))).build();

        CurrencyView currencyView1 = CurrencyView.builder().id(new Long(1)).name("Test1").date(Date.valueOf("2019-10-22"))
                .buy(quoteView1)
                .sell(quoteView3)
                .profit(BigDecimal.valueOf(8.11)).build();
        CurrencyView currencyView2 = CurrencyView.builder().id(new Long(2)).name("Test2").date(Date.valueOf("2019-10-22"))
                .buy(null)
                .sell(null)
                .profit(null).build();

        currencies1 = new ArrayList<>();
        currencies2 = new ArrayList<>();
        currencyViews1 = new ArrayList<>();
        currencyViews2 = new ArrayList<>();

        currencies1.add(currency1);
        currencies2.add(currency2);
        currencyViews1.add(currencyView1);
        currencyViews2.add(currencyView2);
    }

    @Before
    public void contextLoads(){
        assertThat(currencyService).isNotNull();
    }

    @Test
    public void testCurrencyViewValues() throws Exception {
        // Mocking service
        when(currencyClient.retrieveCurrencies()).thenReturn(currencies1);
        List<CurrencyView> list = currencyService.retrieveCurrencyView();

        ObjectMapper mapper = new ObjectMapper();
        Assert.assertEquals(mapper.writeValueAsString(list), mapper.writeValueAsString(currencyViews1));
    }

    @Test
    public void testCurrencyViewNullValues() throws Exception {
        // Mocking service
        when(currencyClient.retrieveCurrencies()).thenReturn(currencies2);
        List<CurrencyView> list = currencyService.retrieveCurrencyView();

        ObjectMapper mapper = new ObjectMapper();
        Assert.assertEquals(mapper.writeValueAsString(list), mapper.writeValueAsString(currencyViews2));
    }

    @Test
    public void testCurrencies() throws Exception {
        // Mocking service
        when(currencyClient.retrieveCurrencies()).thenReturn(currencies1);
        List<Currency> list = currencyService.retrieveCurrencies();

        ObjectMapper mapper = new ObjectMapper();
        Assert.assertEquals(mapper.writeValueAsString(list), mapper.writeValueAsString(currencies1));
    }



}
