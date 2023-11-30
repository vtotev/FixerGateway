package com.task.fixergateway.core;

import org.springframework.data.auditing.DateTimeProvider;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;


public class OffsetDateTimeProvider implements DateTimeProvider {
    ZoneOffset zoneOffset;

    public OffsetDateTimeProvider(ZoneOffset zoneOffset) {
        this.zoneOffset = zoneOffset;
    }

    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(OffsetDateTime.now(zoneOffset));
    }
}
