package com.task.fixergateway.persistence.dto.xml;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@XmlRootElement(name = "rates")
public class XmlResponseWrapperDto implements Serializable {

    private Set<XmlResponseDto> rate = new LinkedHashSet<>();

}
