package com.rogerio.saga.orchestrator.OrchestratorService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class kafkaConfig {
	
	
	@Value("${app.topic.reserve-credit-response}")
	protected String reserveCreditResponseTopic;

	@Value("${app.topic.approve-order-request}")
	protected String approveOrderTopic;

	
	
}
