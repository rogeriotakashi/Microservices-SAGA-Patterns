package com.rogerio.saga.choreography.OrderService.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.rogerio.saga.choreography.OrderService.models.Order;
import com.rogerio.saga.choreography.OrderService.models.requests.CreateOrderRequest;
import com.rogerio.saga.choreography.OrderService.models.response.CreateOrderResponse;
import com.rogerio.saga.choreography.OrderService.services.OrderService;

@Service
public class OrderListener {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderListener.class);
	
	@Value("${app.topic.order-response}")
	private String topic;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	KafkaTemplate<String, CreateOrderResponse> kafkaTemplate;
	
	@KafkaListener(topics = "${app.topic.order-request}", groupId = "CreateOrderRequestGroup", containerFactory="orderKafkaListenerFactory")
	public void createOrder(
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition, 
		    @Header(KafkaHeaders.OFFSET) String offset,
			CreateOrderRequest req) {
		logger.info("Partition {}, offset {}",partition,offset);
		logger.info("Request: {}",req);
		Order order = orderService.createOrder(req.getUser(), req.getTotal());	
		kafkaTemplate.send(topic, new CreateOrderResponse(order));
		logger.info("Finishing");
	}
}
