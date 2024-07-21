package com.szasemkov.pong.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.szasemkov.pong.dto.PongMsgDto;
import com.szasemkov.pong.event.PongEvent;
import com.szasemkov.pong.service.OutMsgEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class PongProducer {

    private static final String TOPIC = "pong.out";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final StreamBridge streamBridge;
    private final OutMsgEventService outMsgEventService;

    public PongProducer(StreamBridge streamBridge, OutMsgEventService outMsgEventService) {
        this.streamBridge = streamBridge;
        this.outMsgEventService = outMsgEventService;
    }

    @Transactional
    public void sendMsgToTopic(PongEvent pongEvent) {

        outMsgEventService.updateStatusToSend(pongEvent.getEventId());

        Message<String> msg = null;
        try {
            msg = MessageBuilder
                    .withPayload(objectMapper.writeValueAsString(new PongMsgDto(pongEvent.getEventId().toString(), pongEvent.getMessage().getMsgDateTime())))
                    .setHeader("name", "pong")
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        streamBridge.send(TOPIC, msg);
        log.info("Sent PongMsg %s".formatted(msg));
    }
}
