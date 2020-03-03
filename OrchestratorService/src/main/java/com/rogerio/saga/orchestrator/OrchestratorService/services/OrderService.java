package com.rogerio.saga.orchestrator.OrchestratorService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rogerio.saga.orchestrator.OrchestratorService.models.OrderDTO;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order.ApproveOrderRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order.CreateOrderRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order.RejectOrderRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.order.CreateOrderResponse;

@Service
public class OrderService {
	
	@Autowired
	RestTemplate rest;
	
	public OrderDTO createOrder(String user, double total) {
		CreateOrderRequest createOrderRequest = new CreateOrderRequest(user, total);
		CreateOrderResponse createOrderResponse = rest.postForObject("http://ORDER-SERVICE/api/v1/order/create", createOrderRequest, CreateOrderResponse.class);
		return createOrderResponse.getOrderDTO();
	}
	
	public ResponseEntity<String> approveOrder(Long orderId) {
		ResponseEntity<?> response = rest.postForEntity("http://ORDER-SERVICE/api/v1/order/approve", new ApproveOrderRequest(orderId) , HttpEntity.class);
		return new ResponseEntity<>(response.getStatusCode());
	}
	
	public ResponseEntity<String> rejectOrder(Long orderId) {
		ResponseEntity<?> response = rest.postForEntity("http://ORDER-SERVICE/api/v1/order/reject", new RejectOrderRequest(orderId) , HttpEntity.class);
		return new ResponseEntity<>(response.getStatusCode());
	}
	
	public ResponseEntity<String> deleteOrder(Long orderId) {
		rest.delete("http://ORDER-SERVICE/api/v1/order/delete/" + orderId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
