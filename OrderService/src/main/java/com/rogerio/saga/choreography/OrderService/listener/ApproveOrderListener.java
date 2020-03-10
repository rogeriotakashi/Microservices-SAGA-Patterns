package com.rogerio.saga.choreography.OrderService.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rogerio.saga.choreography.OrderService.config.KafkaConfig;
import com.rogerio.saga.choreography.OrderService.enums.OrderStatusEnum;
import com.rogerio.saga.choreography.OrderService.models.Order;
import com.rogerio.saga.choreography.OrderService.models.requests.CreateOrderRequest;
import com.rogerio.saga.choreography.OrderService.models.response.CreateOrderResponse;
import com.rogerio.saga.choreography.OrderService.services.OrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApproveOrderListener {

	@Autowired
	KafkaConfig kafkaConfig;	

	@Autowired
	OrderService orderService;


	@KafkaListener(topics = "#{kafkaConfig.approveOrderRequestTopic}", groupId = "ApproveOrderRequestGroup", containerFactory = "approveOrderListenerFactory")
	public void approveOrder(Long orderId) {
		log.info("Entering approveOrder Consumer. Order approved: {}", orderId);
		OrderStatusEnum status = orderService.approveOrder(orderId);
		
		switch(status) {
		case APPROVED:
			log.info("Order status {}. Sending Order ID: {} with approved status to streaming plataform", status, orderId);
			//kafkaTemplate.send(topic, new CreateOrderResponse(order));	
		}
	}
}
