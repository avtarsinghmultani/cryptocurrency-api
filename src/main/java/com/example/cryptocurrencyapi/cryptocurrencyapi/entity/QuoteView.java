package com.example.cryptocurrencyapi.cryptocurrencyapi.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteView {

        private Long id;

        private Time time;

        private BigDecimal price;

//        public QuoteView(Long id, Time time, BigDecimal price)
//        {
//            this.id = id;
//            this.price = price;
//            this.time = time;
//        }
}
