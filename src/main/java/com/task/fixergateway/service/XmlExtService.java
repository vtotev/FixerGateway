package com.task.fixergateway.service;

import com.task.fixergateway.persistence.dto.xml.XmlRequestDto;
import com.task.fixergateway.persistence.dto.xml.XmlResponseDto;

import java.util.List;

public interface XmlExtService {
    XmlResponseDto getCurrentRate(XmlRequestDto request);

    List<XmlResponseDto> getHistoryRates(XmlRequestDto request);
}
