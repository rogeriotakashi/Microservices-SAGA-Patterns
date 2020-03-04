package com.rogerio.saga.orchestrator.OrchestratorService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rogerio.saga.orchestrator.OrchestratorService.enums.OrderStatusEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.models.ProductDTO;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.stock.ProcessOrderRequest;


@Service
public class StockService {
	
	@Autowired
	RestTemplate rest;

	public OrderStatusEnum processOrder(Long orderId, List<ProductDTO> products) {
		ProcessOrderRequest processOrderRequest  = new ProcessOrderRequest(orderId, products);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ProcessOrderRequest> request = new HttpEntity<>(processOrderRequest, headers);
		
		ResponseEntity<HttpEntity> response = rest.exchange(
				"http://STOCK-SERVICE/api/v1/stock/process", 
				HttpMethod.PUT,
				request,
				HttpEntity.class);
		
		switch(response.getStatusCode()) {
			case OK: return OrderStatusEnum.PROCESSED;
			case NOT_FOUND: return OrderStatusEnum.NOT_FOUND;
			case BAD_REQUEST: return OrderStatusEnum.CANCELED;
		default:
			break;
		}
		
		return OrderStatusEnum.CANCELED;
	} 
}
