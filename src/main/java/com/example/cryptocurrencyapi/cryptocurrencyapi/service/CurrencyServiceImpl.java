package com.example.cryptocurrencyapi.cryptocurrencyapi.service;

import com.example.cryptocurrencyapi.cryptocurrencyapi.client.CurrencyClient;
import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.Currency;
import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.CurrencyView;
import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.Quote;
import com.example.cryptocurrencyapi.cryptocurrencyapi.entity.QuoteView;
import com.example.cryptocurrencyapi.cryptocurrencyapi.exception.GenerateCurrencyViewException;
import com.example.cryptocurrencyapi.cryptocurrencyapi.exception.GetCurrencyFailureException;
import com.example.cryptocurrencyapi.cryptocurrencyapi.exception.GetCurrencyViewFailureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyClient currencyClient;

    @Override
    public List<Currency> retrieveCurrencies() {
        List<Currency> list;
        try{
            list = currencyClient.retrieveCurrencies();
            log.info("Successfully retrieved Currencies.");
        }
        catch (Exception e)
        {
            log.error("Failed to retrieve currencies due to: "+ e.getMessage());
            throw new GetCurrencyFailureException();
        }
        return list;
    }

    @Override
    public List<CurrencyView> retrieveCurrencyView() {

        List<Currency> currencyList;
        List<CurrencyView> currencyViews;
        try {
            currencyList = currencyClient.retrieveCurrencies();
            log.info("Successfully retrieved Currency Views");
        } catch (Exception e)
        {
            log.error("Failed to retrieve currencie analysis due to :" + e.getMessage());
            throw new GetCurrencyFailureException();
        }
        try{
            currencyViews = getCurrencyViews(currencyList);
        } catch (Exception e)
        {
            log.error("Failed to retrieve currency analysis due to: "+ e.getMessage());
            throw new GetCurrencyViewFailureException();
        }

        return currencyViews;
    }


    private List<CurrencyView> getCurrencyViews(List<Currency> currencyList) {

        List <CurrencyView> currencyViews = new ArrayList<>();
        //Iterator for future improvements such as running calculations in Threads.
        Iterator<Currency> iterator = currencyList.iterator();
        while(iterator.hasNext())
        {
            CurrencyView currencyView;
            try{
                currencyView = getCurrencyView(iterator.next());
            } catch (Exception e){
                log.error("Failed to retrieve currency Analysis due to: "+ e.getMessage());
                throw new GenerateCurrencyViewException();
            }
            currencyViews.add(currencyView);
        }
        return currencyViews;
    }

    private CurrencyView getCurrencyView(Currency currency) {

        List<Quote> quoteArrayList = new ArrayList<>(currency.getQuotes());
        //Sort with time.
        Collections.sort(quoteArrayList);
        CurrencyView currencyView = fulfilCurrencyView(quoteArrayList, quoteArrayList.size());

        //Check for null.
        if(currencyView == null)
            return null;
        currencyView.setDate(currency.getDate());
        currencyView.setName(currency.getName());
        currencyView.setId(currency.getId());
        return currencyView;
    }

    private CurrencyView fulfilCurrencyView (List<Quote> quoteList, int size)
    {
        CurrencyView currencyView = new CurrencyView();
        if (size > 1){
            // Initialize diff, current
            // sum and max sum
            BigDecimal diff = quoteList.get(1).getPrice().subtract(quoteList.get(0).getPrice());
            BigDecimal curr_sum = diff;
            BigDecimal max_sum = curr_sum;

            //Values initialized for view
            BigDecimal maxVal = quoteList.get(1).getPrice();
            BigDecimal minVal= quoteList.get(0).getPrice();
            Time buyTime = quoteList.get(0).getTime();
            Time sellTime = quoteList.get(1).getTime();
            Long buyId = quoteList.get(0).getId();
            Long sellId = quoteList.get(1).getId();
            int position = 0;
            for(int i = 1; i < size - 1; i++)

            {
                // Calculate current diff
                diff = quoteList.get(i + 1).getPrice().subtract(quoteList.get(i).getPrice());
                // Calculate current sum
                if (curr_sum.compareTo(new BigDecimal(0)) == 1){
                    curr_sum = curr_sum.add(diff);
                }
                else {
                    curr_sum = diff;
                    if(diff.compareTo(new BigDecimal(0)) == 1)
                        position = i;
                }
                // Update max sum, and view values if needed
                if (curr_sum.compareTo(max_sum) == 1) {
                    max_sum = curr_sum;
                    maxVal = quoteList.get(i + 1).getPrice();
                    minVal = quoteList.get(position).getPrice();
                    sellTime = quoteList.get(i+1).getTime();
                    buyTime = quoteList.get(position).getTime();
                    sellId = quoteList.get(1+1).getId();
                    buyId = quoteList.get(position).getId();
                }

            }
            //Create a view with Buy and Sell data types.

            currencyView.setProfit(max_sum);
            currencyView.setBuy(new QuoteView(buyId, buyTime, minVal));
            currencyView.setSell(new QuoteView(sellId, sellTime, maxVal));
        }
        return currencyView;
    }

}
