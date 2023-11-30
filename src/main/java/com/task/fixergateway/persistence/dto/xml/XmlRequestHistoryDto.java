package com.task.fixergateway.persistence.dto.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRequestHistoryDto {

    @XmlAttribute(name = "consumer")
    private String consumer;

    @XmlAttribute(name = "currency")
    private String currency;

    @XmlAttribute(name = "period")
    private Integer period;

}
