package com.rogerio.saga.orchestrator.OrchestratorService.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

import com.rogerio.saga.orchestrator.OrchestratorService.config.KafkaConfig;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.ValidatorResponse;
import com.rogerio.saga.orchestrator.OrchestratorService.services.ValidatorService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ResponseValidatorListener {

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
