package com.szasemkov.ping.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szasemkov.ping.model.OutMsgEvent;
import com.szasemkov.ping.model.OutMsgStatus;
import com.szasemkov.ping.model.OutMsgType;
import com.szasemkov.ping.repository.OutMsgEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.szasemkov.ping.model.OutMsgStatus.NEW;

@Service
public class OutMsgEventService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OutMsgEventRepository outMsgEventRepository;

    public OutMsgEventService(OutMsgEventRepository outMsgEventRepository) {
        this.outMsgEventRepository = outMsgEventRepository;
    }

    public OutMsgEvent saveOutPingMsgEvent(UUID id, String currentDateTime) {

            OutMsgEvent event = outMsgEventRepository.save(new OutMsgEvent(
                    id,
                    currentDateTime,
                    OutMsgType.PING,
                    NEW
            ));
            return event;
    }

    public void updateStatusToSend(UUID id) {
        OutMsgEvent event = outMsgEventRepository.findByUuid(id).get();
        event.setStatus(OutMsgStatus.SENT);
        outMsgEventRepository.save(event);
    }

    public List<OutMsgEvent> getNewEvents() {
        return outMsgEventRepository.findAllByStatus(NEW);
    }
}
