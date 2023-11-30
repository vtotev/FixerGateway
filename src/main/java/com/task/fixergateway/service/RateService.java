package com.task.fixergateway.service;

import com.task.fixergateway.persistence.entity.Rate;

import java.util.List;

public interface RateService {
    Rate getLatestRateForCurrency(String currency);
    List<Rate> getHistoryRatesForCurrency(String currency, Integer period);
}
