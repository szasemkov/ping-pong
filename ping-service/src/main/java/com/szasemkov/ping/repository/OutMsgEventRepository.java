package com.szasemkov.ping.repository;

import com.szasemkov.ping.model.OutMsgEvent;
import com.szasemkov.ping.model.OutMsgStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OutMsgEventRepository extends JpaRepository<OutMsgEvent, Integer> {

    List<OutMsgEvent> findAllByStatus(OutMsgStatus status);

    Optional<OutMsgEvent> findByUuid(UUID id);
}

