package com.szasemkov.ping.controller;

import com.szasemkov.ping.service.PingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    private final PingService pingService;

    public PingController(PingService pingService) {
        this.pingService = pingService;
    }

    @PostMapping("ping")
    public void publishCity() {
        pingService.publishPing();
    }
}
