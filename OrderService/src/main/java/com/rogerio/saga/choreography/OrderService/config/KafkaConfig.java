package com.rogerio.saga.choreography.OrderService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class KafkaConfig {

	@Value("${app.topic.order-request}")
	private String createOrderRequestTopic;
	
	@Value("${app.topic.order-response}")
	private String createOrderResponseTopic;
	
	@Value("${app.topic.approve-order-request}")
	private String approveOrderRequestTopic;
}
