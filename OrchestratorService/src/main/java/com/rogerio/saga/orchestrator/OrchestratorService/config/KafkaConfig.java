package com.rogerio.saga.orchestrator.OrchestratorService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class KafkaConfig {
	
	@Value("${app.topic.order-request}")
	protected String createOrderRequestTopic;
	
	@Value("${app.topic.order-response}")
	protected String createOrderResponseTopic;
	
	@Value("${app.topic.reserve-credit-request}")
	protected String reserveCreditTopic;
	
	@Value("${app.topic.reserve-credit-response}")
	protected String reserveCreditResponseTopic;

	@Value("${app.topic.approve-order-request}")
	protected String approveOrderTopic;
	
	

	
	
}
