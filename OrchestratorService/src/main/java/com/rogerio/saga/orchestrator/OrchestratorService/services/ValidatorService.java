package com.rogerio.saga.orchestrator.OrchestratorService.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rogerio.saga.orchestrator.OrchestratorService.config.KafkaConfig;
import com.rogerio.saga.orchestrator.OrchestratorService.enums.OrderStatusEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.enums.ValidatorEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.models.OrderServiceStatus;
import com.rogerio.saga.orchestrator.OrchestratorService.models.dtos.ApproveValidatorDTO;
import com.rogerio.saga.orchestrator.OrchestratorService.repositories.OrderServicesStatusRepository;
import com.rogerio.saga.orchestrator.OrchestratorService.repositories.ServicesForApprovalRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ValidatorService {

	@Autowired
	OrderServicesStatusRepository orderServiceRepo;

	@Autowired
	OrderService orderService;

	@Autowired
	ServicesForApprovalRepository servicesForApprovalRepo;

	@Autowired
	KafkaTemplate<String, Long> kafkaTemplate;

	@Autowired
	KafkaConfig kafkaConfig;

	public void createOrderServiceStatus(Long orderId, ValidatorEnum status, String serviceName) {
		OrderServiceStatus orderServiceStatus = new OrderServiceStatus(orderId, status, serviceName);
		orderServiceRepo.saveAndFlush(orderServiceStatus);
	}

	public void getOrderServicesStatusByOrdersId(List<Long> orderIdList) {
		HashMap<Long, List<OrderServiceStatus>> orderServicesStatusMap = new HashMap<>();

		orderIdList.stream().forEach(orderId -> {
			Optional<List<OrderServiceStatus>> orderServicesStatusOpt = orderServiceRepo.findByOrderId(orderId);
			orderServicesStatusOpt.ifPresent(orderServiceStatus -> {
				orderServicesStatusMap.put(orderId, orderServiceStatus);
			});
		});

		// Validate if all services for approval verification is available
		int expectedNumberOfServices = (int) servicesForApprovalRepo.count();

		// For each Order
		orderServicesStatusMap.forEach((orderId, orderServicesStatusList) -> {

			// verify if received all services before approve validation
			if (expectedNumberOfServices == orderServicesStatusList.size()) {
				ApproveValidatorDTO approveValidator = isOrderApproved(orderServicesStatusList);
				if (approveValidator.isApproved()) {
					kafkaTemplate.send(kafkaConfig.getApproveOrderTopic(), orderId);
				} else {
					// Sync call to Reject order before calling compensation methods
					OrderStatusEnum orderStatus = orderService.rejectOrder(orderId);
					if (OrderStatusEnum.REJECTED.equals(orderStatus)) {
						kafkaTemplate.send(kafkaConfig.getCompensateOrderTopic(), orderId);
					}
				}
			}
		});
	}

	private ApproveValidatorDTO isOrderApproved(List<OrderServiceStatus> orderServiceStatusList) {
		Optional<OrderServiceStatus> failedOrderServiceOpt = orderServiceStatusList.stream()
				.filter(ValidatorService::isServiceFailed).findFirst();
		Optional<ApproveValidatorDTO> approveValidatorOpt = failedOrderServiceOpt
				.map((failedOrderService) -> new ApproveValidatorDTO(false, failedOrderService));

		return approveValidatorOpt.orElse(new ApproveValidatorDTO(true));
	}

	private static boolean isServiceFailed(OrderServiceStatus orderServiceStatus) {
		return ValidatorEnum.valueOf(orderServiceStatus.getStatus()).equals(ValidatorEnum.FAILED);
	}

}
