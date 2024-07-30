package com.szasemkov.pong.service;

import com.szasemkov.pong.event.PongEvent;
import com.szasemkov.pong.kafka.PongProducer;
import com.szasemkov.pong.model.OutMsgEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PongService {

    private final PongProducer pongProducer;
    private final OutMsgEventService outMsgEventService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public PongService(PongProducer pongProducer, OutMsgEventService outMsgEventService, ApplicationEventPublisher applicationEventPublisher) {
        this.pongProducer = pongProducer;
        this.outMsgEventService = outMsgEventService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public void publishPong() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        OutMsgEvent eventPongEvent = outMsgEventService.saveOutCityMsgEvent(UUID.randomUUID(), currentDateTime.toString());

        PongEvent pongEvent = new PongEvent(this, eventPongEvent.getUuid(), eventPongEvent);
        applicationEventPublisher.publishEvent(pongEvent);
    }
}
