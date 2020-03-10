package com.rogerio.saga.orchestrator.OrchestratorService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.rogerio.saga.orchestrator.OrchestratorService.config.KafkaConfig;

import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order.CreateOrderRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	
	@Autowired
	KafkaConfig kafkaConfig;
	
	@Autowired
	RestTemplate rest;
	
	@Autowired
	KafkaTemplate<String, CreateOrderRequest> kafkaTemplate;
	
	public void createOrder(String user, double total) {
		log.info("Entering createOrder method. user {}, total {}", user, total);
		CreateOrderRequest createOrderRequest = new CreateOrderRequest(user, total);
		log.info("Created CreateOrderRequest. Sending to streaming plataform");
		kafkaTemplate.send(kafkaConfig.getCreateOrderRequestTopic(), createOrderRequest);
	}
}
