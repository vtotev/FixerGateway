package com.task.fixergateway.persistence.dto.xml;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRequestGetDto {
    @XmlAttribute(name = "consumer")
    private String consumer;
    @XmlElement(name = "currency")
    private String currency;
}
