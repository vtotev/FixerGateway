package com.task.fixergateway.persistence.dto.json;

import lombok.Data;

@Data
public class JsonRequestDto {
    private String requestId;
    private long timestamp;
    private String client;
    private String currency;

}
