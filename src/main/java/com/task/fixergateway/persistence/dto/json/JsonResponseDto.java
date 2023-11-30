package com.task.fixergateway.persistence.dto.json;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class JsonResponseDto {
    private String baseCurrency;
    private String currency;
    private double rate;
    private OffsetDateTime timestamp;
}
