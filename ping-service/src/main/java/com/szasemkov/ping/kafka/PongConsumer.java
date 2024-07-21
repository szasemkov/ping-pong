package com.szasemkov.ping.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PongConsumer {

    private static final String TOPIC = "pong.out";

    @KafkaListener(topics = TOPIC, groupId = "ping-pong")
    public void listen(String message) {
        log.info("Consumed message: %s".formatted(message));
    }
}
