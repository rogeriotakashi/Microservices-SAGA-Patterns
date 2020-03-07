package com.rogerio.saga.choreography.CustomerService.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.rogerio.saga.choreography.CustomerService.models.requests.ReserveCreditRequest;

@Service
public class ReserveCreditListener {

	@KafkaListener(topics = "${app.topic.reserve-credit-request}" , groupId = "ReserveCreditRequestGroup", containerFactory="reserveCreditKafkaListenerFactory")
	public void reserveCreditRequestListener(ReserveCreditRequest request) {
		System.out.println(request);
	}
}
