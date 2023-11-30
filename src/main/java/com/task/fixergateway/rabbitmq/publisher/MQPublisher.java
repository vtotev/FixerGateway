package com.task.fixergateway.rabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MQPublisher {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER  = LoggerFactory.getLogger(MQPublisher.class);

    public void sendMessage(Object message) {
        LOGGER.info(String.format("Message send -> %s", message.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

}
