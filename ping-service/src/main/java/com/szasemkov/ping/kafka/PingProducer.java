package com.szasemkov.ping.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.szasemkov.ping.dto.PingMsgDto;
import com.szasemkov.ping.event.PingEvent;
import com.szasemkov.ping.service.OutMsgEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class PingProducer {

    private static final String TOPIC = "ping.out";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final StreamBridge streamBridge;
    private final OutMsgEventService outMsgEventService;

    public PingProducer(StreamBridge streamBridge, OutMsgEventService outMsgEventService) {
        this.streamBridge = streamBridge;
        this.outMsgEventService = outMsgEventService;
    }

    @Transactional
    public void sendMsgToTopic(PingEvent pingEvent) {

        outMsgEventService.updateStatusToSend(pingEvent.getEventId());

        Message<String> msg = null;
        try {
            msg = MessageBuilder
                    .withPayload(objectMapper.writeValueAsString(new PingMsgDto(pingEvent.getEventId().toString(), pingEvent.getMessage().getMsgDateTime())))
                    .setHeader("name", "ping")
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        streamBridge.send(TOPIC, msg);
        log.info("Sent PingMsg %s".formatted(msg));
    }
}
