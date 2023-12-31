package com.task.fixergateway.web;

import com.task.fixergateway.persistence.dto.json.JsonRequestHistoryDto;
import com.task.fixergateway.persistence.dto.json.JsonRequestDto;
import com.task.fixergateway.persistence.dto.json.JsonResponseDto;
import com.task.fixergateway.service.JsonExtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.Set;

@RestController
@RequestMapping("/json_api")
@RequiredArgsConstructor
public class JsonApiController {

    @Autowired
    public RestClient restClient;
    @Autowired
    private JsonExtService service;

    @PostMapping(value = "/current", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponseDto> postCurrent(@RequestBody JsonRequestDto requestDto) {
        return ResponseEntity.ok(service.getCurrencyLatestRate(requestDto));
    }

    @PostMapping(value = "/history", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<JsonResponseDto>> postHistory(@RequestBody JsonRequestHistoryDto requestDto) {
       return ResponseEntity.ok(service.getCurrencyRateHistoryForPeriod(requestDto));
    }

}
