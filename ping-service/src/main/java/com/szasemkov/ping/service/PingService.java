package com.szasemkov.ping.service;

import com.szasemkov.ping.event.PingEvent;
import com.szasemkov.ping.kafka.PingProducer;
import com.szasemkov.ping.model.OutMsgEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PingService {

    private final PingProducer pingProducer;
    private final OutMsgEventService outMsgEventService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public PingService(PingProducer pingProducer, OutMsgEventService outMsgEventService, ApplicationEventPublisher applicationEventPublisher) {
        this.pingProducer = pingProducer;
        this.outMsgEventService = outMsgEventService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public void publishPing() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        OutMsgEvent eventPingEvent = outMsgEventService.saveOutPingMsgEvent(UUID.randomUUID(), currentDateTime.toString());

        PingEvent pingEvent = new PingEvent(this, eventPingEvent.getUuid(), eventPingEvent);
        applicationEventPublisher.publishEvent(pingEvent);
    }
}
