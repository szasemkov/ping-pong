package com.szasemkov.pong.event;

import com.szasemkov.pong.model.OutMsgEvent;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
public class PongEvent extends ApplicationEvent {

    private final UUID eventId;
    private final OutMsgEvent message;

    public PongEvent(Object source, UUID eventId, OutMsgEvent message) {
        super(source);
        this.eventId = eventId;
        this.message = message;
    }

}
