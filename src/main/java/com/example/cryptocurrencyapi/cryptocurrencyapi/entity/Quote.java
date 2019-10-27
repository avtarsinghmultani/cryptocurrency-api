package com.example.cryptocurrencyapi.cryptocurrencyapi.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Time;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote implements Comparable< Quote >{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotNull
    private Time time;

    @NotNull
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="currency_id", nullable=false)
    @JsonIgnore
    private Currency currency;

    @Override
    public int compareTo(Quote o) {
        return this.getTime().compareTo(o.getTime());
    }
}
