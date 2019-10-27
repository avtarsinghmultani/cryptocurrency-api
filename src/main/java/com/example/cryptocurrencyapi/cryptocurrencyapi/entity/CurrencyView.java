package com.example.cryptocurrencyapi.cryptocurrencyapi.entity;

import com.sun.org.apache.xpath.internal.operations.Quo;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyView {

    private Long id;

    private String name;

    private Date date;

    private QuoteView buy;

    private QuoteView sell;

    private BigDecimal profit;

    private static AtomicInteger uniqueId=new AtomicInteger();

}
