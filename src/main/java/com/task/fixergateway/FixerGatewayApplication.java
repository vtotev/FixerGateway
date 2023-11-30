package com.task.fixergateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing(dateTimeProviderRef = "offsetDateTimeProvider")
@EnableScheduling
@SpringBootApplication
public class FixerGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FixerGatewayApplication.class, args);
    }

}
