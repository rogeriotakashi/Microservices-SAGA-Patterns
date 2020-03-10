package com.rogerio.saga.choreography.OrderService.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rogerio.saga.choreography.OrderService.enums.OrderStatusEnum;
import com.rogerio.saga.choreography.OrderService.models.Order;
import com.rogerio.saga.choreography.OrderService.models.requests.CreateOrderRequest;
import com.rogerio.saga.choreography.OrderService.models.response.CreateOrderResponse;
import com.rogerio.saga.choreography.OrderService.services.OrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderListener {

	@Value("${app.topic.order-request}")
	private String createOrderRequestTopic;
	
	@Value("${app.topic.order-response}")
	private String createOrderResponseTopic;
	
	@Value("${app.topic.approve-order-request}")
	private String approveOrderResponseTopic;
	

	@Autowired
	OrderService orderService;

	@Autowired
	KafkaTemplate<String, CreateOrderResponse> kafkaTemplate;

	@KafkaListener(topics = "${app.topic.order-request}", groupId = "CreateOrderRequestGroup", containerFactory = "orderKafkaListenerFactory")
	public void createOrder(CreateOrderRequest req) {
		log.info("Entering CreateOrderRequest Consumer. Request: {}", req);
		Order order = orderService.createOrder(req.getUser(), req.getTotal());
		
		log.info("Order created {}. Sending CreateOrderResponse to streaming plataform", order);
		kafkaTemplate.send(createOrderResponseTopic, new CreateOrderResponse(order));
	}
	
	@KafkaListener(topics = "${app.topic.approve-order-request}", groupId = "ApproveOrderRequestGroup", containerFactory = "approveOrderListenerFactory")
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
