package com.task.fixergateway.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MQMessageDto {
    private String serviceName;
    private String requestId;
    private Timestamp time;
    private String client;

}
