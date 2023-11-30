package com.task.fixergateway.persistence.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class FixerResponseDto {
    private boolean success;
    private int timestamp;
    private String base;
    private LocalDate date;
    private Map<String, Double> rates;
}
