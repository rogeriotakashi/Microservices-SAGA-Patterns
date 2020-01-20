package com.rogerio.saga.choreography.CustomerService.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rogerio.saga.choreography.CustomerService.models.requests.ReserveCreditRequest;
import com.rogerio.saga.choreography.CustomerService.services.CustomerService;


@RestController
@RequestMapping("api/v1/customer")
public class CustomerResource {
	
	@Autowired
	CustomerService customerService;

	@PostMapping("/reserve-credit")
	public HttpEntity<String> reserveCredit(@RequestBody ReserveCreditRequest req) {
		customerService.reserveCredit(req.getUser(), req.getTotal());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
