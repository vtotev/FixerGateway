package com.task.fixergateway.service;

import com.task.fixergateway.persistence.entity.Rate;

import java.util.stream.Stream;

public interface RateService {
    Rate getLatestRateForCurrency(String currency);
    Stream<Rate> getHistoryRatesForCurrency(String currency, Integer period);
}
