package com.rogerio.saga.choreography.OrderService.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.rogerio.saga.choreography.OrderService.models.Order;
import com.rogerio.saga.choreography.OrderService.models.requests.CreateOrderRequest;
import com.rogerio.saga.choreography.OrderService.models.response.CreateOrderResponse;
import com.rogerio.saga.choreography.OrderService.services.OrderService;

@Service
public class OrderListener {
	
	@Value("${app.topic.order-response}")
	private String topic;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	KafkaTemplate<String, CreateOrderResponse> kafkaTemplate;
	
	@KafkaListener(topics = "${app.topic.order-request}", groupId = "CreateOrderRequestGroup", containerFactory="orderKafkaListenerFactory")
	public void createOrder(CreateOrderRequest req) {
		Order order = orderService.createOrder(req.getUser(), req.getTotal());	
		kafkaTemplate.send(topic, new CreateOrderResponse(order));
	}
}
