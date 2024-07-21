package com.szasemkov.ping.event;

import com.szasemkov.ping.model.OutMsgEvent;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
public class PingEvent extends ApplicationEvent {

    private final UUID eventId;
    private final OutMsgEvent message;

    public PingEvent(Object source, UUID eventId, OutMsgEvent message) {
        super(source);
        this.eventId = eventId;
        this.message = message;
    }

}
