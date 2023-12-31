package com.task.fixergateway.service.impl;

import com.task.fixergateway.persistence.dto.json.JsonRequestHistoryDto;
import com.task.fixergateway.persistence.dto.json.JsonRequestDto;
import com.task.fixergateway.persistence.dto.json.JsonResponseDto;
import com.task.fixergateway.persistence.entity.Rate;
import com.task.fixergateway.service.JsonExtService;
import com.task.fixergateway.service.RateService;
import com.task.fixergateway.service.StatisticsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static com.task.fixergateway.core.ServiceName.JSON_SERVICE_NAME;

@Service
public class JsonExtServiceImpl implements JsonExtService {

    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private RateService rateService;
    @Autowired
    private ModelMapper mapper;

    private JsonResponseDto mapRateToDto(Rate rate) {
        return mapper.map(rate, JsonResponseDto.class);
    }

    @Override
    public JsonResponseDto getCurrencyLatestRate(JsonRequestDto request) {
        statisticsService.validateRequest(request.getRequestId());
        statisticsService.createRecord(JSON_SERVICE_NAME, request.getRequestId(), request.getClient());
        return mapRateToDto(rateService.getLatestRateForCurrency(request.getCurrency()));
    }

    @Override
    public Set<JsonResponseDto> getCurrencyRateHistoryForPeriod(JsonRequestHistoryDto request) {
        statisticsService.validateRequest(request.getRequestId());
        statisticsService.createRecord(JSON_SERVICE_NAME, request.getRequestId(), request.getClient());
        return rateService.getHistoryRatesForCurrency(request.getCurrency(), request.getPeriod()).stream()
                .map(this::mapRateToDto).collect(Collectors.toSet());
    }

}
