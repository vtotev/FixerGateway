package com.task.fixergateway.web;

import com.task.fixergateway.persistence.dto.xml.XmlRequestDto;
import com.task.fixergateway.persistence.dto.xml.XmlResponseWrapperDto;
import com.task.fixergateway.service.XmlExtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@RestController
@RequestMapping("/xml_api")
public class XmlApiController {

    @Autowired
    private XmlExtService service;

    @Transactional
    @PostMapping(value = "/command", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<XmlResponseWrapperDto> postCommand(@RequestBody XmlRequestDto request) {
        XmlResponseWrapperDto result = new XmlResponseWrapperDto();

        if (request.getGet() != null) {
            result.setRate(Set.of(service.getCurrentRate(request)));
            return ResponseEntity.ok(result);
        }

        if (request.getHistory() != null) {
            result.setRate(service.getHistoryRates(request));
        }

        return ResponseEntity.ok(result);
    }

}
