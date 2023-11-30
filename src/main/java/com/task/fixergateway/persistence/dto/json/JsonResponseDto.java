package com.task.fixergateway.persistence.dto.json;

import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
public class JsonResponseDto implements Serializable {
    private String baseCurrency;
    private String currency;
    private double rate;
    private OffsetDateTime timestamp;
}
