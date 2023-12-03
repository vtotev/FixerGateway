package com.task.fixergateway.service.impl;

import com.task.fixergateway.persistence.dto.FixerResponseDto;
import com.task.fixergateway.persistence.dto.MQMessageDto;
import com.task.fixergateway.persistence.entity.Rate;
import com.task.fixergateway.persistence.repository.RateRepository;
import com.task.fixergateway.rabbitmq.publisher.MQPublisher;
import com.task.fixergateway.service.FixerApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static com.task.fixergateway.core.ErrorMessages.UNABLE_TO_READ_FROM_FIXER;
import static com.task.fixergateway.core.InformationMessages.FIXER_LOAD_SUCCESSFUL;
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

    private static final Logger log  = LoggerFactory.getLogger(FixerApiServiceImpl.class);

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
            throw new IllegalStateException(UNABLE_TO_READ_FROM_FIXER);
        }

        List<Rate> rates = data.getRates().entrySet()
                .stream()
                .map(r -> Rate.builder()
                        .baseCurrency(data.getBase())
                        .currency(r.getKey())
                        .rate(r.getValue())
                        .timestamp(Timestamp.from(Instant.ofEpochSecond(data.getTimestamp())))
                        .build())
                .toList();

        repository.saveAll(rates);

        log.info(FIXER_LOAD_SUCCESSFUL);

        MQMessageDto message = new MQMessageDto(FIXER_RATES_LOAD, null, Timestamp.from(Instant.now()), null);
        mqPublisher.sendMessage(message);

        invalidateCache();
    }
}
