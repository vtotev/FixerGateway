package com.task.fixergateway.service.impl;

import com.task.fixergateway.persistence.dto.FixerResponseDto;
import com.task.fixergateway.persistence.dto.MQMessageDto;
import com.task.fixergateway.persistence.entity.Rate;
import com.task.fixergateway.persistence.repository.RateRepository;
import com.task.fixergateway.rabbitmq.publisher.MQPublisher;
import com.task.fixergateway.service.FixerApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Timestamp;
import java.time.Instant;

import static com.task.fixergateway.core.ServiceName.FIXER_RATES_LOAD;

@Service
public class FixerApiServiceImpl implements FixerApiService {

    @Autowired
    public RestClient restClient;
    @Autowired
    private RateRepository repository;
    @Autowired
    private MQPublisher mqPublisher;

    @Value("${fixer_io.baseUrl}")
    private String fixerUrl;

    @Value("${fixer_io.access_key}")
    private String accessKey;

    @Value("${fixer_io.path_latest}")
    private String pathLatest;

    @CacheEvict(allEntries = true, value = {"JsonResponseDto", "JsonResponseDtoList", "XmlResponseDto", "XmlResponseDtoList"})
    public void invalidateCache() {
    }

    @Override
    @Scheduled(initialDelayString = "${fixer_io.scheduler.load-initial-delay}", fixedDelayString = "${fixer_io.scheduler.load-fixed-delay}")
    public void loadRates() {
        String uri = UriComponentsBuilder.fromUriString(fixerUrl)
                .path(pathLatest)
                .queryParam("access_key", accessKey)
                .build().toUriString();

        FixerResponseDto data = restClient.get()
                .uri(uri)
                .retrieve()
                .body(FixerResponseDto.class);

        if (data == null || data.getRates() == null) {
            throw new IllegalStateException("Unable to read data from Fixer.io");
        }

        data.getRates().forEach((k, v) -> {
            Rate rate = Rate.builder()
                    .baseCurrency(data.getBase())
                    .currency(k)
                    .rate(v)
                    .timestamp(Timestamp.from(Instant.ofEpochSecond(data.getTimestamp())))
                    .build();

            repository.save(rate);
        });

        MQMessageDto message = new MQMessageDto(FIXER_RATES_LOAD, null, Timestamp.from(Instant.now()), null);
        mqPublisher.sendMessage(message);

        invalidateCache();
    }
}
