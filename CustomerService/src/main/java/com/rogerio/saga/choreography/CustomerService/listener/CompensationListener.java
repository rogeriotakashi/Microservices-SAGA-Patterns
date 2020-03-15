package com.rogerio.saga.choreography.CustomerService.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import com.rogerio.saga.choreography.CustomerService.config.KafkaConfig;
import com.rogerio.saga.choreography.CustomerService.models.dtos.ChargebackDTO;
import com.rogerio.saga.choreography.CustomerService.services.CustomerService;
import com.rogerio.saga.choreography.CustomerService.services.OperationHistoryService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CompensationListener {

	@Autowired
	KafkaConfig kafkaConfig;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	OperationHistoryService operatioHistoryService;
	
	
	@KafkaListener(topics = "#{kafkaConfig.compensateOrderTopic}", groupId = "CompensateOrderGroupId")
	public void compensateOrderListener(Long orderId) {
		log.info("Entering Compensation Order Listener for Order ID: {}", orderId);
		
		// Get username and total to chargeback
		ChargebackDTO chargeback = operatioHistoryService.getChargebackByOrderId(orderId);
		
		// Register the operation
		operatioHistoryService.chargebackOperation(orderId);
		
		// Apply to Customer register
		customerService.chargebackCustomerByUsername(chargeback);
		
	}
}
