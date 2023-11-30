package com.task.fixergateway.service;

public interface FixerApiService {

    void loadRates();
    void invalidateCache();
}
