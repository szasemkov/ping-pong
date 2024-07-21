package com.szasemkov.pong.event;

import com.szasemkov.pong.kafka.PongProducer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PongEventListener {

    private final PongProducer pongProducer;

    public PongEventListener(PongProducer pongProducer) {
        this.pongProducer = pongProducer;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleCityEvent(PongEvent pongEvent) {
        pongProducer.sendMsgToTopic(pongEvent);
    }
}
