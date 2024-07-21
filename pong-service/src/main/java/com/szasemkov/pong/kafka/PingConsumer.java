package com.szasemkov.pong.kafka;

import com.szasemkov.pong.service.PongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PingConsumer {

    private static final String TOPIC = "ping.out";

    private final PongService pongService;

    public PingConsumer(PongService pongService) {
        this.pongService = pongService;
    }

    @KafkaListener(topics = TOPIC, groupId = "ping-pong")
    public void listen(String message) {
        log.info("Consumed message: %s".formatted(message));
        pongService.publishPong();
    }
}