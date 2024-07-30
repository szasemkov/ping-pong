package com.szasemkov.pong.repository;

import com.szasemkov.pong.model.OutMsgEvent;
import com.szasemkov.pong.model.OutMsgStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OutMsgEventRepository extends JpaRepository<OutMsgEvent, Integer> {

    List<OutMsgEvent> findAllByStatus(OutMsgStatus status);

    Optional<OutMsgEvent> findByUuid(UUID id);
}
