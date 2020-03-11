package com.rogerio.saga.orchestrator.OrchestratorService.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rogerio.saga.orchestrator.OrchestratorService.config.KafkaConfig;
import com.rogerio.saga.orchestrator.OrchestratorService.enums.ReserveStatusEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.customer.ReserveCreditResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReserveCreditListener {

	@Autowired
	KafkaConfig kafkaConfig;
	
	@Autowired
	private KafkaTemplate<String, Long> kafkaTemplate;

	@KafkaListener(topics = "#{kafkaConfig.reserveCreditResponseTopic}", groupId = "ReserveCreditResponseGroup")
	public void reserveCreditResponseListener(ReserveCreditResponse response) {
		log.info("Receiving response from CustomerService producer. Response: {} ", response);
		ReserveStatusEnum status = response.getStatus();

		switch (status) {		
		case RESERVED:
			kafkaTemplate.send(kafkaConfig.getApproveOrderRequestTopic(), response.getOrderId());
			break;
		case INSUFICIENT_CREDIT:
		case CUSTOMER_NOT_FOUND:
			/*
			 * TODO: Implement a producer to send a message to cancel the order and execution of compensation functions 
			 * CancelOrderRequest CancelOrderRequest = new CancelOrderRequest(); 
			 * kafkaTemplate.send("CancelOrderRequestTopic",CancelOrderRequest);
			 */
			break;
		}

	}

}
