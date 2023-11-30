package com.task.fixergateway.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Builder;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 3, nullable = false)
    private String baseCurrency;

    @Column(length = 3, nullable = false)
    private String currency;

    @Column(nullable = false)
    private double rate;

    private Timestamp timestamp;

}


