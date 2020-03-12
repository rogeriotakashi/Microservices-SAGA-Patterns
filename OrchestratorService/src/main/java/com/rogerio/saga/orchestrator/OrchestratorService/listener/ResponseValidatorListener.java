package com.rogerio.saga.orchestrator.OrchestratorService.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.rogerio.saga.orchestrator.OrchestratorService.config.KafkaConfig;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.customer.ReserveCreditResponse;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.stock.ProcessOrderResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResponseValidatorListener {

	@Autowired
	KafkaConfig kafkaConfig;
	
	
	
	@KafkaListener(topics = {"#{kafkaConfig.responseValidatorTopic}"}, groupId = "ResponseValidatorGroup")
	public void responseValidatorListener(ProcessOrderResponse p) {
		log.info("Validating the response!");
	}
	
}
