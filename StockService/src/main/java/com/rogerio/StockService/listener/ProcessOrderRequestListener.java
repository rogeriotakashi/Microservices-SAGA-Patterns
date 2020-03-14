package com.rogerio.StockService.listener;

import java.util.EnumMap;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

import com.rogerio.StockService.config.KafkaConfig;
import com.rogerio.StockService.enums.ProcessStatusEnum;
import com.rogerio.StockService.enums.ValidatorEnum;
import com.rogerio.StockService.models.ValidatorResponse;
import com.rogerio.StockService.models.requests.ProcessOrderRequest;
import com.rogerio.StockService.services.StockService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProcessOrderRequestListener {
	
	@Autowired
	KafkaConfig kafkaConfig;
	
	@Autowired
	StockService stockService;
	
	@Autowired
	KafkaTemplate<String, ValidatorResponse> kafkaTemplate;
	
	@Value("${spring.application.name}")
	private String SERVICE_NAME;

	@KafkaListener(topics = "#{kafkaConfig.processOrderRequestTopic}", groupId = "ProcessOrderRequestGroup" )
	public void processOrderRequestListener(ProcessOrderRequest request) {
		log.info("Entering processOrderRequestListener. Consuming the request {}",request);
		ProcessStatusEnum status = stockService.processOrder(request.getProductsOrdered());
		
		EnumMap<ProcessStatusEnum, Supplier<ValidatorResponse>> map = new EnumMap<>(ProcessStatusEnum.class);
		map.put(ProcessStatusEnum.SUCCESS, () -> new ValidatorResponse(request.getOrderId(), ValidatorEnum.OK, SERVICE_NAME));
		map.put(ProcessStatusEnum.PRODUCT_NOT_FOUND, () -> new ValidatorResponse(request.getOrderId(), ValidatorEnum.FAILED, SERVICE_NAME));
		map.put(ProcessStatusEnum.PRODUCT_NOT_AVAILIBLE, () -> new ValidatorResponse(request.getOrderId(), ValidatorEnum.FAILED, SERVICE_NAME));

		Supplier<ValidatorResponse> validator = map.get(status);
		
		kafkaTemplate.send(kafkaConfig.getResponseValidatorTopic(), validator.get());
			
		
	}
}
