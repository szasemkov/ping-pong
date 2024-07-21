package com.szasemkov.ping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PingMsgDto {

    private String ping_id;
    private String pingDateTime;
}
