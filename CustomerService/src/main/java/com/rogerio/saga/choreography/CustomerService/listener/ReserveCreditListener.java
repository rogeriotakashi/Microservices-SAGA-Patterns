package com.rogerio.saga.choreography.CustomerService.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rogerio.saga.choreography.CustomerService.enums.ReserveStatusEnum;
import com.rogerio.saga.choreography.CustomerService.models.requests.ReserveCreditRequest;
import com.rogerio.saga.choreography.CustomerService.models.response.ReserveCreditResponse;
import com.rogerio.saga.choreography.CustomerService.services.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReserveCreditListener {
	
	@Value("${app.topic.reserve-credit-response}")
	private String reserveCreditResponseTopic;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	KafkaTemplate<String, ReserveCreditResponse> kafkaTemplate;

	@KafkaListener(topics = "${app.topic.reserve-credit-request}" , groupId = "ReserveCreditRequestGroup", containerFactory="reserveCreditKafkaListenerFactory")
	public void reserveCreditRequestListener(ReserveCreditRequest request) {
		log.info("Entering ReserveCrediRequest consumer. Request: {}", request);
		ReserveStatusEnum status = customerService.reserveCredit(request.getUser(), request.getTotal());
		kafkaTemplate.send(reserveCreditResponseTopic, new ReserveCreditResponse(request.getOrderId(), status));
	}
}
