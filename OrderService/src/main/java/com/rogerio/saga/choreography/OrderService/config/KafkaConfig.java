package com.rogerio.saga.choreography.OrderService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class KafkaConfig {

	@Value("${app.topic.pending-orders}")
	private String pendingOrdersTopic;
	
	@Value("${app.topic.approve-order}")
	protected String approveOrderTopic;
}
