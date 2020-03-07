package com.rogerio.saga.orchestrator.OrchestratorService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class OrchestratorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrchestratorServiceApplication.class, args);
	}

}
