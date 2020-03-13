package com.rogerio.saga.orchestrator.OrchestratorService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rogerio.saga.orchestrator.OrchestratorService.enums.ValidatorEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.models.OrderServicesStatus;
import com.rogerio.saga.orchestrator.OrchestratorService.repositories.OrderServicesStatusRepository;

@Service
public class ValidatorService {
	
	@Autowired
	OrderServicesStatusRepository repo;

	public void createOrderServiceStatus(Long orderId, ValidatorEnum status, String serviceName) {
		OrderServicesStatus orderServiceStatus = new OrderServicesStatus(orderId, status, serviceName);
		repo.saveAndFlush(orderServiceStatus);
	}

}
