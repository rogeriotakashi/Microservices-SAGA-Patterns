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
import com.rogerio.saga.choreography.CustomerService.models.requests.ApproveRequest;
import com.rogerio.saga.choreography.CustomerService.models.requests.ReserveCreditRequest;
import com.rogerio.saga.choreography.CustomerService.services.CustomerService;




@RestController
@RequestMapping("api/v1/customer")
public class CustomerResource {
	
	@Autowired
	RestTemplate rest;
	
	@Autowired
	CustomerService customerService;

	@PostMapping("/reserve-credit")
	public HttpEntity<String> reserveCredit(@RequestBody ReserveCreditRequest req) {
		CustomerStatusEnum status = customerService.reserveCredit(req.getUser(), req.getTotal());		
		if(status == CustomerStatusEnum.RESERVED) {
			ResponseEntity<?> response = rest.postForEntity("http://ORDER-SERVICE/api/v1/order/approve", new ApproveRequest(req.getOrderId()) , HttpEntity.class);
			return new ResponseEntity<>(response.getStatusCode());
		}
		
		rest.delete("http://ORDER-SERVICE/api/v1/order/delete/" + req.getOrderId());
		return new ResponseEntity<>(HttpStatus.OK);	
	}
}
