package com.rogerio.saga.orchestrator.OrchestratorService.listener;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rogerio.saga.orchestrator.OrchestratorService.config.KafkaConfig;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReserveCreditListener {

	@Autowired
	KafkaConfig kafkaConfig;
	
	@Autowired
	private KafkaTemplate<String, Long> kafkaTemplate;

	@KafkaListener(topics = "#{kafkaConfig.responseValidatorTopic}", groupId = "ResponseValidatorGroup")
	public void responseValidatorListener(ConsumerRecord<String, String> record) {
		log.info("Entering Response Validator Listener for record "+ record.value());
		try {
			HashMap<String,Object> result = new ObjectMapper().readValue(record.value(), HashMap.class);
			log.info(result.get("orderId").toString());
			log.info(result.get("status").toString());
			
		} catch (JsonMappingException e) {
			// TODO: Add logs
		} catch (JsonProcessingException e) {
			// TODO: Add logs
		}
	}

}
