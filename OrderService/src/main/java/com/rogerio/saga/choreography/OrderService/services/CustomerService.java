package com.rogerio.saga.choreography.OrderService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rogerio.saga.choreography.OrderService.models.Order;
import com.rogerio.saga.choreography.OrderService.models.requests.ReserveCreditRequest;

@Service
public class CustomerService {
	
	@Autowired
	RestTemplate rest;
	
	public ResponseEntity<?> reserveCredit(Order order) {
		ReserveCreditRequest reserveCreditRequest = new ReserveCreditRequest(order.getUser(), order.getTotal(), order.getId());
		ResponseEntity<?> reservCredtiResponse = rest.postForEntity("http://CUSTOMER-SERVICE/api/v1/customer/reserve-credit", reserveCreditRequest, HttpEntity.class);
		return reservCredtiResponse;
	}
}
