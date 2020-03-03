package com.rogerio.saga.choreography.CustomerService.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rogerio.saga.choreography.CustomerService.enums.CustomerStatusEnum;
import com.rogerio.saga.choreography.CustomerService.models.requests.OrderResultRequest;
import com.rogerio.saga.choreography.CustomerService.models.requests.ReserveCreditRequest;
import com.rogerio.saga.choreography.CustomerService.services.CustomerService;
import com.rogerio.saga.choreography.CustomerService.services.OrderService;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerResource {
	
	@Autowired
	RestTemplate rest;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	OrderService orderService;

	@PostMapping("/reserve-credit")
	public HttpEntity<String> reserveCredit(@RequestBody ReserveCreditRequest req) {
		CustomerStatusEnum status = customerService.reserveCredit(req.getUser(), req.getTotal());
		ResponseEntity<?> response = null;
		
		switch(status) {	
			case RESERVED:
				response = orderService.approve(req.getOrderId());
				break;
			case INSUFICIENT_CREDIT:
				response = orderService.reject(req.getOrderId());
				break;
			case CUSTOMER_NOT_FOUND:
				orderService.delete(req.getOrderId());
		}
		HttpStatus responseStatus = response != null ? response.getStatusCode() : HttpStatus.OK;
		return new ResponseEntity<>(responseStatus);	
	}
}
