package com.task.fixergateway.service;

import com.task.fixergateway.persistence.dto.xml.XmlRequestDto;
import com.task.fixergateway.persistence.dto.xml.XmlResponseDto;

import java.util.Set;

public interface XmlExtService {
    XmlResponseDto getCurrentRate(XmlRequestDto request);

    Set<XmlResponseDto> getHistoryRates(XmlRequestDto request);
}
