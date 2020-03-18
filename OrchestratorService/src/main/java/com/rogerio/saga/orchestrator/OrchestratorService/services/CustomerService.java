package com.rogerio.saga.orchestrator.OrchestratorService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rogerio.saga.orchestrator.OrchestratorService.config.KafkaConfig;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.customer.ReserveCreditRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {
	
	@Autowired
	KafkaConfig kafkaConfig;
	
	@Autowired
	KafkaTemplate<String, ReserveCreditRequest> kafkaTemplate;
	
	public void reserveCredit(Long orderId, String user, double total) {
		ReserveCreditRequest reserveCreditRequest = new ReserveCreditRequest(user, total, orderId);
		log.info("Created ReserveCreditRequest. Sending to streaming plataform");
		kafkaTemplate.send(kafkaConfig.getReserveCreditRequestTopic(), reserveCreditRequest);
	}
	
}
