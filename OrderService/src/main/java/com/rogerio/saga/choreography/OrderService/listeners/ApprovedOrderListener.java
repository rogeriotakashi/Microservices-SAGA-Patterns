package com.rogerio.saga.choreography.OrderService.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import com.rogerio.saga.choreography.OrderService.config.KafkaConfig;
import com.rogerio.saga.choreography.OrderService.services.OrderService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ApprovedOrderListener {

	@Autowired
	KafkaConfig kafkaConfig;
	
	@Autowired
	OrderService orderService;
	
	@KafkaListener(topics = "#{kafkaConfig.approveOrderTopic}", groupId = "ApprovedOrderListenerGroup")
	public void approvedOrderListener(Long orderId) {
		log.info("Entering Approved Order Listener. Order ID: {}", orderId);	
		orderService.approveOrder(orderId);
	}
}
