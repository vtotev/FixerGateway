package com.task.fixergateway.service;

public interface StatisticsService {

    void createRecord(String serviceName, String requestId, String client);
    void validateRequest(String requestId);
}
