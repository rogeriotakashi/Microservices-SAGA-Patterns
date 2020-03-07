package com.rogerio.saga.orchestrator.OrchestratorService.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.customer.ReserveCreditRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.order.CreateOrderResponse;

@Service
public class OrderListener {
	
	@Autowired
	KafkaTemplate<String, ReserveCreditRequest> kafkaTemplate;

	@KafkaListener(topics = "${app.topic.order-response}", groupId = "group_json", containerFactory = "orderKafkaListenerFactory")
	public void orderCreatedResponseListener(CreateOrderResponse response) {
		
	}
}
