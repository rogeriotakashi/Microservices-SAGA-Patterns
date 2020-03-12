package com.rogerio.saga.orchestrator.OrchestratorService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import lombok.Getter;

@Configuration
@Getter
public class KafkaConfig {
	
	@Value("${app.topic.order-request}")
	protected String createOrderRequestTopic;
	
	@Value("${app.topic.order-response}")
	protected String createOrderResponseTopic;
	
	@Value("${app.topic.reserve-credit-request}")
	protected String reserveCreditRequestTopic;
	
	@Value("${app.topic.reserve-credit-response}")
	protected String reserveCreditResponseTopic;

	@Value("${app.topic.approve-order-request}")
	protected String approveOrderRequestTopic;
	
	@Value("${app.topic.approve-order-response}")
	protected String approveOrderResponseTopic;
	
	@Value("${app.topic.process-order-request}")
	protected String processOrderRequestTopic;
	
	@Value("${app.topic.response-validator}")
	protected String responseValidatorTopic;	
	
	
	/* This bean is necessary to convert the  Message (Json) to String for StringDeserialization on @KafkaListener (Consumer)*/
	@Bean
	public StringJsonMessageConverter converter() {
		return new StringJsonMessageConverter();
	}
	
	
}
