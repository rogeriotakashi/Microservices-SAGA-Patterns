package com.rogerio.saga.orchestrator.OrchestratorService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rogerio.saga.orchestrator.OrchestratorService.config.KafkaConfig;
import com.rogerio.saga.orchestrator.OrchestratorService.models.OrderDTO;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order.CreateOrderRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.order.CreateOrderResponse;

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
	
	public OrderDTO createOrder(String user, double total) {
		CreateOrderRequest createOrderRequest = new CreateOrderRequest(user, total);
		CreateOrderResponse createOrderResponse = rest.postForObject("http://ORDER-SERVICE/api/v1/order/create", createOrderRequest, CreateOrderResponse.class);
		return createOrderResponse.getOrderDTO();
	}
}
