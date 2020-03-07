package com.rogerio.saga.orchestrator.OrchestratorService.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rogerio.saga.orchestrator.OrchestratorService.enums.OrderStatusEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.models.OrderDTO;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.customer.ReserveCreditRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.order.CreateOrderResponse;

@Service
public class OrderListener {
	
	@Value("${app.topic.reserve-credit-request}")
	private String reserveCreditTopic;
	
	@Autowired
	KafkaTemplate<String, ReserveCreditRequest> kafkaTemplate;

	@KafkaListener(topics = "${app.topic.order-response}", groupId = "CreateOrderResponseGroup", containerFactory = "orderKafkaListenerFactory")
	public void orderCreatedResponseListener(CreateOrderResponse response) {
		OrderDTO order = response.getOrderDTO();
		OrderStatusEnum status = OrderStatusEnum.fromInteger(order.getStatus());
		
		ReserveCreditRequest reserveCreditRequest = new ReserveCreditRequest(order.getUser(),order.getTotal(),order.getId());
		switch(status) {
			case PENDING:
				kafkaTemplate.send(reserveCreditTopic, reserveCreditRequest);				
			default:
				// TODO: handdle when we don't get the PENDING status!
		}
	}
}
