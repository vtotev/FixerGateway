package com.task.fixergateway.rabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.task.fixergateway.core.InformationMessages.RABBIT_MQ_MESSAGE_SENT;

@Service
public class MQPublisher {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger log  = LoggerFactory.getLogger(MQPublisher.class);

    public void sendMessage(Object message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        log.info(String.format(RABBIT_MQ_MESSAGE_SENT, message));
    }

}
