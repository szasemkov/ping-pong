package com.szasemkov.ping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PingServiceApplication.class, args);
	}

}
