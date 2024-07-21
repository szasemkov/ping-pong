package com.szasemkov.pong.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szasemkov.pong.model.OutMsgEvent;
import com.szasemkov.pong.model.OutMsgStatus;
import com.szasemkov.pong.model.OutMsgType;
import com.szasemkov.pong.repository.OutMsgEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.szasemkov.pong.model.OutMsgStatus.NEW;

@Service
public class OutMsgEventService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OutMsgEventRepository outMsgEventRepository;

    public OutMsgEventService(OutMsgEventRepository outMsgEventRepository) {
        this.outMsgEventRepository = outMsgEventRepository;
    }

    public OutMsgEvent saveOutCityMsgEvent(UUID id, String currentDateTime) {

        OutMsgEvent event = outMsgEventRepository.save(new OutMsgEvent(
                id,
                currentDateTime,
                OutMsgType.PONG,
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
