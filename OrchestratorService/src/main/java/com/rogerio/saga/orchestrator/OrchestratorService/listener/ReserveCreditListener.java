package com.rogerio.saga.orchestrator.OrchestratorService.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rogerio.saga.orchestrator.OrchestratorService.enums.ReserveStatusEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order.ApproveOrderRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order.RejectOrderRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.customer.ReserveCreditResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReserveCreditListener {

	@Value("${app.topic.reserve-credit-response}")
	private String reserveCreditResponseTopic;
	
	@Value("${app.topic.approve-order-request}")
	private String approveOrderRequestTopic;

	@Autowired
	private KafkaTemplate<String, ApproveOrderRequest> kafkaTemplate;

	@KafkaListener(topics = "${app.topic.reserve-credit-response}", groupId = "ReserveCreditResponseGroup", containerFactory = "reseveCreditResponseListenerFactory")
	public void reserveCreditResponseListener(ReserveCreditResponse response) {
		log.info("Receiving response from CustomerService producer. Response: {} ", response);
		ReserveStatusEnum status = response.getStatus();

		switch (status) {
		case RESERVED:
			ApproveOrderRequest approveOrderRequest = new ApproveOrderRequest();
			kafkaTemplate.send(approveOrderRequestTopic, approveOrderRequest);
			break;
		case INSUFICIENT_CREDIT:
		case CUSTOMER_NOT_FOUND:
			/*
			 * TODO: Implement a producer to send a message to cancel the order and execution of compensation functions 
			 * CancelOrderRequest CancelOrderRequest = new CancelOrderRequest();
			 * kafkaTemplate.send("CancelOrderRequestTopic", CancelOrderRequest);
			 */
			break;
		}

	}

}
