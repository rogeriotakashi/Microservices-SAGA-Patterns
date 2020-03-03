package com.rogerio.saga.orchestrator.OrchestratorService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.stock.ProcessOrderRequest;


@Service
public class StockService {
	
	@Autowired
	RestTemplate rest;

	public ResponseEntity<?> processOrder() {
		//ProcessOrderRequest processOrderRequest  = new ProcessOrderRequest();
		//return rest.postForEntity("http://STOCK-SERVICE/api/v1/stock/processOrder", processOrderRequest, HttpEntity.class);
		return null;
	} 
}
