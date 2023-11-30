package com.task.fixergateway.persistence.dto.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlResponseDto {

    @XmlElement(name = "baseCurrency")
    private String baseCurrency;

    @XmlElement(name = "currency")
    private String currency;

    @XmlElement(name = "rate")
    private double rate;

    @XmlElement(name = "timestamp")
    private String timestamp;

}
