package com.rogerio.StockService.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rogerio.StockService.config.KafkaConfig;
import com.rogerio.StockService.enums.ProcessStatusEnum;
import com.rogerio.StockService.models.requests.ProcessOrderRequest;
import com.rogerio.StockService.models.responses.ProcessOrderResponse;
import com.rogerio.StockService.services.StockService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProcessOrderRequestListener {
	
	@Autowired
	KafkaConfig kafkaConfig;
	
	@Autowired
	StockService stockService;
	
	@Autowired
	KafkaTemplate<String, ProcessOrderResponse> kafkaTemplate;

	@KafkaListener(topics = "#{kafkaConfig.processOrderRequestTopic}", groupId = "ProcessOrderRequestGroup" )
	public void processOrderRequestListener(ProcessOrderRequest request) {
		log.info("Entering processOrderRequestListener. Consuming the request {}",request);
		ProcessStatusEnum status = stockService.processOrder(request.getProductsOrdered());
		ProcessOrderResponse response = new ProcessOrderResponse(request.getOrderId(), status);
		
		kafkaTemplate.send(kafkaConfig.getResponseValidatorTopic(), response);
			
		
	}
}
