package com.task.fixergateway.service.impl;

import com.task.fixergateway.persistence.dto.xml.XmlRequestDto;
import com.task.fixergateway.persistence.dto.xml.XmlResponseDto;
import com.task.fixergateway.persistence.entity.Rate;
import com.task.fixergateway.service.RateService;
import com.task.fixergateway.service.StatisticsService;
import com.task.fixergateway.service.XmlExtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.task.fixergateway.core.ServiceName.XML_SERVICE_NAME;

@Service
public class XmlExtServiceImpl implements XmlExtService {
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private RateService rateService;
    @Autowired
    private ModelMapper mapper;

    private XmlResponseDto mapRateToDto(Rate rate) {
        XmlResponseDto map = mapper.map(rate, XmlResponseDto.class);
        map.setTimestamp(rate.getTimestamp().toInstant().toString());
        return map;
    }

    @Override
    public XmlResponseDto getCurrentRate(XmlRequestDto request) {
        statisticsService.validateRequest(request.getId());
        statisticsService.createRecord(XML_SERVICE_NAME, request.getId(), request.getGet().getConsumer());
        return mapRateToDto(rateService.getLatestRateForCurrency(request.getGet().getCurrency()));
    }

    @Override
    public List<XmlResponseDto> getHistoryRates(XmlRequestDto request) {
        statisticsService.validateRequest(request.getId());
        statisticsService.createRecord(XML_SERVICE_NAME, request.getId(), request.getHistory().getConsumer());
        return rateService.getHistoryRatesForCurrency(request.getHistory().getCurrency(), request.getHistory().getPeriod())
                .stream()
                .map(this::mapRateToDto)
                .toList();
    }
}
