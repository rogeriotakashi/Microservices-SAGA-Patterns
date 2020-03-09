package com.rogerio.saga.orchestrator.OrchestratorService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.RestTemplate;

import com.rogerio.saga.orchestrator.OrchestratorService.enums.OrderStatusEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order.ApproveOrderRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order.CreateOrderRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order.RejectOrderRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order.UpdateOrderStatusRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.order.ApproveOrderResponse;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.order.RejectOrderResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	
	@Value("${app.topic.order-request}")
	private String topic;
	
	@Autowired
	RestTemplate rest;
	
	@Autowired
	KafkaTemplate<String, CreateOrderRequest> kafkaTemplate;
	
	public void createOrder(String user, double total) {
		log.info("Entering createOrder method. user {}, total {}", user, total);
		CreateOrderRequest createOrderRequest = new CreateOrderRequest(user, total);
		log.info("Created CreateOrderRequest. Sending to streaming plataform");
		ListenableFuture<SendResult<String,CreateOrderRequest>> future = kafkaTemplate.send(topic, createOrderRequest);
		future.addCallback(new ListenableFutureCallback<SendResult<String, CreateOrderRequest>>() {
			@Override
			public void onSuccess(SendResult<String, CreateOrderRequest> result) {
				log.info("Request sent: {}, Partition sent: {}, with offset: {}", 
						createOrderRequest,
						result.getRecordMetadata().partition(),
						result.getRecordMetadata().offset());	
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Unable to send message: {} due to: {}", createOrderRequest, ex.getMessage());
			}
		});
	}
	
	public OrderStatusEnum approveOrder(Long orderId) {
		ApproveOrderRequest approveOrderRequest = new ApproveOrderRequest(orderId);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ApproveOrderRequest> request = new HttpEntity<>(approveOrderRequest, headers);
		
		ResponseEntity<ApproveOrderResponse> approveOrderResponse = rest.exchange(
				"http://ORDER-SERVICE/api/v1/order/approve", 
				HttpMethod.PUT,
				request,
				ApproveOrderResponse.class);
		
		return approveOrderResponse.getBody().getStatus();
	}
	
	public OrderStatusEnum rejectOrder(Long orderId) {
		RejectOrderRequest rejectOrderRequest = new RejectOrderRequest(orderId);
		RejectOrderResponse response = rest.postForObject("http://ORDER-SERVICE/api/v1/order/reject", rejectOrderRequest , RejectOrderResponse.class);
		return response.getStatus();
	}
	
	public OrderStatusEnum deleteOrder(Long orderId) {
		rest.delete("http://ORDER-SERVICE/api/v1/order/delete/" + orderId);
		return OrderStatusEnum.DELETED;
	}

	public void updateOrderStatus(Long orderId, OrderStatusEnum status) {
		UpdateOrderStatusRequest updateOrderStatusRequest = new UpdateOrderStatusRequest(orderId, status);		
		rest.put("http://ORDER-SERVICE/api/v1/order/update",updateOrderStatusRequest);
		
	}

}
