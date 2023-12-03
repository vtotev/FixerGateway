package com.task.fixergateway.service.impl;

import com.task.fixergateway.exception.DataNotFoundException;
import com.task.fixergateway.persistence.entity.Rate;
import com.task.fixergateway.persistence.repository.RateRepository;
import com.task.fixergateway.service.RateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;

import static com.task.fixergateway.core.ErrorMessages.DATA_NOT_FOUND_EXCEPTION;
import static com.task.fixergateway.core.InformationMessages.HISTORY_FOR_CURRENCY_REQUESTED;
import static com.task.fixergateway.core.InformationMessages.LATEST_CURRENCY_REQUESTED;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository repository;
    private static final Logger log  = LoggerFactory.getLogger(RateServiceImpl.class);

    @Override
    public Rate getLatestRateForCurrency(String currency) {
        Rate rate = repository.findTopByCurrencyOrderByTimestampDesc(currency)
                .orElseThrow(() -> new DataNotFoundException(String.format(DATA_NOT_FOUND_EXCEPTION, currency)));
        log.info(String.format(LATEST_CURRENCY_REQUESTED, currency));
        return rate;
    }

    @Override
    public Stream<Rate> getHistoryRatesForCurrency(String currency, Integer period) {
        Instant startInstant = LocalDateTime.now().atOffset(ZoneOffset.UTC).minusHours(period).toInstant();
        log.info(String.format(HISTORY_FOR_CURRENCY_REQUESTED, currency));
        return repository.findAllByCurrencyAndTimestampAfterOrderByTimestampDesc(currency, Timestamp.from(startInstant));
    }

}
