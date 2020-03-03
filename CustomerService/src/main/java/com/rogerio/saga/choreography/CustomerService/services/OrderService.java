package com.rogerio.saga.choreography.CustomerService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rogerio.saga.choreography.CustomerService.models.requests.OrderResultRequest;

@Service
public class OrderService {
	
	@Autowired
	RestTemplate rest;
	
	public ResponseEntity<?> approve(Long id) {
		return rest.postForEntity("http://ORDER-SERVICE/api/v1/order/approve", new OrderResultRequest(id) , HttpEntity.class);
	}

	public ResponseEntity<?> reject(Long id) {
		return rest.postForEntity("http://ORDER-SERVICE/api/v1/order/reject", new OrderResultRequest(id) , HttpEntity.class);
	}
	
	public void delete(Long id) {
		rest.delete("http://ORDER-SERVICE/api/v1/order/delete/" + id);
	}
}
