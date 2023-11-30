package com.task.fixergateway.config;

import com.task.fixergateway.core.OffsetDateTimeProvider;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Configuration
public class AppConfig {

    @Bean
    public ZoneOffset defaultTimezone() {
        return ZoneOffset.UTC;
    }

    @Bean
    public OffsetDateTimeProvider offsetDateTimeProvider() {
        return new OffsetDateTimeProvider(defaultTimezone());
    }

    @Bean
    public ModelMapper modelMapper() {
        AbstractConverter<Timestamp, OffsetDateTime> converter = new AbstractConverter<>() {
            @Override
            protected OffsetDateTime convert(Timestamp timestamp) {
                return timestamp.toInstant().atOffset(ZoneOffset.UTC);
            }
        };

        ModelMapper mapper = new ModelMapper();
        mapper.addConverter(converter);

        return mapper;
    }

    @Bean
    public RestClient restClient() {
        return RestClient.builder().build();
    }

}
