package com.task.fixergateway.web;

import com.task.fixergateway.persistence.dto.xml.XmlRequestDto;
import com.task.fixergateway.persistence.dto.xml.XmlResponseDto;
import com.task.fixergateway.persistence.dto.xml.XmlResponseWrapperDto;
import com.task.fixergateway.service.XmlExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/xml_api")
public class XmlApiController {

    @Autowired
    private XmlExtService service;

    @PostMapping(value = "/command", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<XmlResponseWrapperDto> postCommand(@RequestBody XmlRequestDto request) {
        XmlResponseWrapperDto result = new XmlResponseWrapperDto();

        if (request.getGet() != null) {
            result.setRate(List.of(service.getCurrentRate(request)));
            return ResponseEntity.ok(result);
        }

        if (request.getHistory() != null) {
            List<XmlResponseDto> historyRates = service.getHistoryRates(request);
            result.setRate(historyRates);
        }

        return ResponseEntity.ok(result);
    }

}
