package com.szasemkov.pong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PongMsgDto {

    private String pong_id;

    private String pingDateTime;
}
