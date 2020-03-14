package com.rogerio.saga.orchestrator.OrchestratorService.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rogerio.saga.orchestrator.OrchestratorService.config.PendingOrderProperties;
import com.rogerio.saga.orchestrator.OrchestratorService.enums.ValidatorEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.models.OrderServiceStatus;
import com.rogerio.saga.orchestrator.OrchestratorService.models.ApproveValidatorDTO;
import com.rogerio.saga.orchestrator.OrchestratorService.repositories.OrderServicesStatusRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ValidatorService {
	
	@Autowired
	OrderServicesStatusRepository repo;
	
	@Autowired
	PendingOrderProperties pendingOrderProperties;
	

	public void createOrderServiceStatus(Long orderId, ValidatorEnum status, String serviceName) {
		OrderServiceStatus orderServiceStatus = new OrderServiceStatus(orderId, status, serviceName);
		repo.saveAndFlush(orderServiceStatus);
	}
	
	public void getOrderServicesStatusByOrdersId(List<Long> orderIdList) {
		HashMap<Long, List<OrderServiceStatus>> orderServicesStatusMap = new HashMap<>();
		
		orderIdList.stream().forEach(orderId -> {
			Optional<List<OrderServiceStatus>> orderServicesStatusOpt = repo.findByOrderId(orderId);
			orderServicesStatusOpt.ifPresent( orderServiceStatus -> {
				orderServicesStatusMap.put(orderId, orderServiceStatus);
			});
		});
		
		// List of services to be verified from properties
		List<String> serviceVerificationList = pendingOrderProperties.getServiceVerificationList();
		
		// For each Order
		orderServicesStatusMap.forEach((orderId, orderServicesStatusList) -> {
			ApproveValidatorDTO approveValidator = isOrderApproved(orderServicesStatusList);
			
			if(approveValidator.isApproved()) {
				// TODO: Kafka Send approved status
			} else {
				// TODO: Kafka Send compensation event to all the services
			}
		});
	}
	
	private ApproveValidatorDTO isOrderApproved(List<OrderServiceStatus> orderServiceStatusList) {	
		Optional<OrderServiceStatus> failedOrderServiceOpt = orderServiceStatusList.stream()
				.filter(ValidatorService::isServiceFailed)
				.findFirst();		
		Optional<ApproveValidatorDTO> approveValidatorOpt = failedOrderServiceOpt.map((failedOrderService) -> new ApproveValidatorDTO(false, failedOrderService));
		
		return approveValidatorOpt.orElse(new ApproveValidatorDTO(true));
	}
	
	
	private static boolean isServiceFailed(OrderServiceStatus orderServiceStatus) {
		return ValidatorEnum.valueOf(orderServiceStatus.getStatus()).equals(ValidatorEnum.FAILED);
	}

}
