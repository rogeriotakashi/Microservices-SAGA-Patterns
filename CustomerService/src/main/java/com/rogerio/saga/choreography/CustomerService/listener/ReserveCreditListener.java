package com.rogerio.saga.choreography.CustomerService.listener;

import java.util.EnumMap;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

import com.rogerio.saga.choreography.CustomerService.config.KafkaConfig;
import com.rogerio.saga.choreography.CustomerService.enums.ReserveStatusEnum;
import com.rogerio.saga.choreography.CustomerService.enums.ValidatorEnum;
import com.rogerio.saga.choreography.CustomerService.models.ValidatorResponse;
import com.rogerio.saga.choreography.CustomerService.models.requests.ReserveCreditRequest;
import com.rogerio.saga.choreography.CustomerService.services.CustomerService;
import com.rogerio.saga.choreography.CustomerService.services.OperationHistoryService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ReserveCreditListener {
	
	@Autowired
	KafkaConfig kafkaConfig;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	OperationHistoryService operationHistoryService;
	
	@Autowired
	KafkaTemplate<String, ValidatorResponse> kafkaTemplate;
	
	@Value("${spring.application.name}")
	private String SERVICE_NAME;

	@KafkaListener(topics = "#{kafkaConfig.reserveCreditRequestTopic}" , groupId = "ReserveCreditRequestGroup")
	public void reserveCreditRequestListener(ReserveCreditRequest request) {
		log.info("Entering ReserveCrediRequest consumer. Request: {}", request);
		ReserveStatusEnum status = customerService.reserveCredit(request.getUser(), request.getTotal());
		operationHistoryService.debtOperation(request.getOrderId(), request.getUser(), request.getTotal());
		
		EnumMap<ReserveStatusEnum, Supplier<ValidatorResponse>> map = new EnumMap<>(ReserveStatusEnum.class);
		map.put(ReserveStatusEnum.RESERVED, () -> new ValidatorResponse(request.getOrderId(), ValidatorEnum.OK, SERVICE_NAME));
		map.put(ReserveStatusEnum.CUSTOMER_NOT_FOUND, () -> new ValidatorResponse(request.getOrderId(), ValidatorEnum.FAILED, SERVICE_NAME));
		map.put(ReserveStatusEnum.INSUFICIENT_CREDIT, () -> new ValidatorResponse(request.getOrderId(), ValidatorEnum.FAILED, SERVICE_NAME));
		
		Supplier<ValidatorResponse> validator = map.get(status);

		kafkaTemplate.send(kafkaConfig.getResponseValidatorTopic(), validator.get());
	}
}
