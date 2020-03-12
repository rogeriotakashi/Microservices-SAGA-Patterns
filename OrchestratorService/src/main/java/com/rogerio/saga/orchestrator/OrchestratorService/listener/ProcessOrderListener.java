//package com.rogerio.saga.orchestrator.OrchestratorService.listener;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//import com.rogerio.saga.orchestrator.OrchestratorService.config.KafkaConfig;
//import com.rogerio.saga.orchestrator.OrchestratorService.enums.ProcessStatusEnum;
//import com.rogerio.saga.orchestrator.OrchestratorService.models.response.stock.ProcessOrderResponse;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Service
//@Slf4j
//public class ProcessOrderListener {
//
//	@Autowired
//	KafkaConfig kafkaConfig;
//	
//	@Autowired
//	private KafkaTemplate<String, Long> kafkaTemplate;
//
//	@KafkaListener(topics = "#{kafkaConfig.processOrderResponseTopic}", groupId = "ProcessOrderGroupId")
//	public void reserveCreditResponseListener(ProcessOrderResponse response) {
//		log.info("Receiving response from StockService producer. Response: {} ", response);
//		ProcessStatusEnum status = response.getStatus();
//
//		switch (status) {
//		case SUCCESS:
//			break;			
//		case PRODUCT_NOT_AVAILIBLE:
//		case PRODUCT_NOT_FOUND:
//			/*
//			 * TODO: Implement a producer to send a message to cancel the order and execution of compensation functions 
//			 * CancelOrderRequest CancelOrderRequest = new CancelOrderRequest(); 
//			 * kafkaTemplate.send("CancelOrderRequestTopic",CancelOrderRequest);
//			 */
//		}
//
//	}
//
//}
