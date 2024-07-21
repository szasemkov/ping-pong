package com.szasemkov.ping.event;

import com.szasemkov.ping.kafka.PingProducer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PingEventListener {

    private final PingProducer pingProducer;

    public PingEventListener(PingProducer pingProducer) {
        this.pingProducer = pingProducer;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleCityEvent(PingEvent pingEvent) {
        pingProducer.sendMsgToTopic(pingEvent);
    }

}
