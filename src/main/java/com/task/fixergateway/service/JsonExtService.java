package com.task.fixergateway.service;

import com.task.fixergateway.persistence.dto.json.JsonRequestHistoryDto;
import com.task.fixergateway.persistence.dto.json.JsonRequestDto;
import com.task.fixergateway.persistence.dto.json.JsonResponseDto;

import java.util.stream.Stream;

public interface JsonExtService {

    JsonResponseDto getCurrencyLatestRate(JsonRequestDto request);
    Stream<JsonResponseDto> getCurrencyRateHistoryForPeriod(JsonRequestHistoryDto request);
}
