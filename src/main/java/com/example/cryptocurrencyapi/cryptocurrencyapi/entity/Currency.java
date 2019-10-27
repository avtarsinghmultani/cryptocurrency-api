package com.example.cryptocurrencyapi.cryptocurrencyapi.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @NotNull
    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    private Date date;

    @OneToMany(mappedBy="currency")
    @OrderBy("time ASC")
    private Set<Quote> quotes;
}
