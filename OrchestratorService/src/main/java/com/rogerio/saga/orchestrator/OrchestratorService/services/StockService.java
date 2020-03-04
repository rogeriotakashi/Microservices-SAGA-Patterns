package com.rogerio.saga.orchestrator.OrchestratorService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rogerio.saga.orchestrator.OrchestratorService.enums.OrderStatusEnum;


@Service
public class StockService {
	
	@Autowired
	RestTemplate rest;

	public OrderStatusEnum processOrder(Long orderId) {
		//ProcessOrderRequest processOrderRequest  = new ProcessOrderRequest();
		//return rest.postForEntity("http://STOCK-SERVICE/api/v1/stock/processOrder", processOrderRequest, HttpEntity.class);
		return null;
	} 
}
