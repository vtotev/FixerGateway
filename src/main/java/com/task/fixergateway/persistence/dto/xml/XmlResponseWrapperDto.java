package com.task.fixergateway.persistence.dto.xml;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "rates")
public class XmlResponseWrapperDto {

    private List<XmlResponseDto> rate = new ArrayList<>();

}
