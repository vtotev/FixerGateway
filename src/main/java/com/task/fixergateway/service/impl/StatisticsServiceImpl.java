package com.task.fixergateway.service.impl;

import com.task.fixergateway.exception.ConflictException;
import com.task.fixergateway.persistence.dto.MQMessageDto;
import com.task.fixergateway.persistence.entity.Statistics;
import com.task.fixergateway.persistence.repository.StatisticsRepository;
import com.task.fixergateway.rabbitmq.publisher.MQPublisher;
import com.task.fixergateway.service.StatisticsService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

import static com.task.fixergateway.core.ErrorMessages.VALIDATION_FAILED;
import static com.task.fixergateway.core.InformationMessages.STATISTICS_SAVED;
import static com.task.fixergateway.core.InformationMessages.VALIDATION_SUCCESSFUL;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsRepository repository;
    @Autowired
    private MQPublisher mqPublisher;
    @Autowired
    private ModelMapper mapper;
    private static final Logger log  = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    private void sendMQMessage(Statistics statistics) {
        MQMessageDto message = mapper.map(statistics, MQMessageDto.class);
        mqPublisher.sendMessage(message);
    }
    @Override
    public void createRecord(String serviceName, String requestId, String client) {
        Statistics newEntity = Statistics.builder()
                .serviceName(serviceName)
                .requestId(requestId)
                .client(client)
                .time(Timestamp.from(Instant.now()))
                .build();

        Statistics entity = repository.save(newEntity);
        log.info(String.format(STATISTICS_SAVED, entity.getServiceName()));
        sendMQMessage(entity);
    }

    @Override
    public void validateRequest(String requestId) {
        if (repository.findByRequestId(requestId).isPresent()) {
            throw new ConflictException(String.format(VALIDATION_FAILED, requestId));
        }
        log.info(String.format(VALIDATION_SUCCESSFUL, requestId));
    }
}
