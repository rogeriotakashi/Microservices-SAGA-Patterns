package com.rogerio.saga.orchestrator.OrchestratorService.listener;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rogerio.saga.orchestrator.OrchestratorService.config.KafkaConfig;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.ValidatorResponse;
import com.rogerio.saga.orchestrator.OrchestratorService.services.ValidatorService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResposnseValidatorListener {

	@Autowired
	KafkaConfig kafkaConfig;
	
	@Autowired
	private KafkaTemplate<String, Long> kafkaTemplate;
	
	@Autowired
	private ValidatorService validatorService;

	@KafkaListener(topics = "#{kafkaConfig.responseValidatorTopic}", groupId = "ResponseValidatorGroup")
	public void responseValidatorListener(ValidatorResponse res) {
		log.info("Entering Response Validator Listener for response "+ res);
		validatorService.createOrderServiceStatus(res.getOrderId(), res.getStatus(), res.getServiceName());
		
	}

}
