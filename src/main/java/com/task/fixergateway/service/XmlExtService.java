package com.task.fixergateway.service;

import com.task.fixergateway.persistence.dto.xml.XmlRequestDto;
import com.task.fixergateway.persistence.dto.xml.XmlResponseDto;

import java.util.stream.Stream;

public interface XmlExtService {
    XmlResponseDto getCurrentRate(XmlRequestDto request);

    Stream<XmlResponseDto> getHistoryRates(XmlRequestDto request);
}
