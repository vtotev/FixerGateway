package com.task.fixergateway.persistence.dto.xml;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlRootElement(name = "command")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRequestDto {

    @XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "get")
    private XmlRequestGetDto get;

    @XmlElement(name = "history")
    private XmlRequestHistoryDto history;

}
