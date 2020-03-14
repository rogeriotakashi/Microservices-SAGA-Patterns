package com.rogerio.saga.orchestrator.OrchestratorService.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import com.rogerio.saga.orchestrator.OrchestratorService.config.KafkaConfig;
import com.rogerio.saga.orchestrator.OrchestratorService.services.ValidatorService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PendingOrdersListener {

	@Autowired
	KafkaConfig kafkaConfig;
	
	@Autowired
	ValidatorService validatorService;
	
	
	@KafkaListener(topics = "#{kafkaConfig.pendingOrdersTopic}", groupId="PendingOrdersGroupId")
	public void processPendingOrders(List<Long> pendingOrdersIds) {
		log.info("Entering Process Pending Order listener. Received orders ids {}", pendingOrdersIds );
		validatorService.getOrderServicesStatusByOrdersId(pendingOrdersIds);
		
	}
}
