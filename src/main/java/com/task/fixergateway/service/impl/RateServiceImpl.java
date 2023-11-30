package com.task.fixergateway.service.impl;

import com.task.fixergateway.persistence.entity.Rate;
import com.task.fixergateway.persistence.repository.RateRepository;
import com.task.fixergateway.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository repository;

    @Override
    public Rate getLatestRateForCurrency(String currency) {
        return repository.findLatestRate(currency).orElse(null);
    }

    @Override
    public Stream<Rate> getHistoryRatesForCurrency(String currency, Integer period) {
        Instant startInstant = LocalDateTime.now().atOffset(ZoneOffset.UTC).minusHours(period).toInstant();
        return repository.findAllByCurrencyOrderByTimestampDesc(currency, Timestamp.from(startInstant));
    }

}
