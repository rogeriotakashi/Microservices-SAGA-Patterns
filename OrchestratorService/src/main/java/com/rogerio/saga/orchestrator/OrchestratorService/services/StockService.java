package com.rogerio.saga.orchestrator.OrchestratorService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rogerio.saga.orchestrator.OrchestratorService.config.KafkaConfig;
import com.rogerio.saga.orchestrator.OrchestratorService.models.ProductDTO;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.stock.ProcessOrderRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockService {

	@Autowired
	KafkaConfig kafkaConfig;
	
	@Autowired
	KafkaTemplate<String, ProcessOrderRequest> kafkaTemplate;
	
	
	public void processOrder(Long orderId, List<ProductDTO> products) {
		ProcessOrderRequest processOrderRequest  = new ProcessOrderRequest(orderId, products);
		log.info("Created ProcessOrderRequest. Sending to streaming plataform");
		kafkaTemplate.send(kafkaConfig.getProcessOrderRequestTopic(), processOrderRequest);
	}
	
}
