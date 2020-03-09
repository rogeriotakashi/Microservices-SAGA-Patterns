package com.rogerio.saga.choreography.CustomerService.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.rogerio.saga.choreography.CustomerService.models.requests.ReserveCreditRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReserveCreditListener {

	@KafkaListener(topics = "${app.topic.reserve-credit-request}" , groupId = "ReserveCreditRequestGroup", containerFactory="reserveCreditKafkaListenerFactory")
	public void reserveCreditRequestListener(ReserveCreditRequest request) {
		log.info("Entering ReserveCrediRequest consumer. Request: {}", request);
	}
}
