package com.szasemkov.ping.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutMsgEvent {

    @Id
    @Column(name="id", columnDefinition = "uuid")
    private UUID uuid;

    private String msgDateTime;

    @Enumerated(EnumType.STRING)
    private OutMsgType outMsgType;

    @Enumerated(EnumType.STRING)
    private OutMsgStatus status;

}
