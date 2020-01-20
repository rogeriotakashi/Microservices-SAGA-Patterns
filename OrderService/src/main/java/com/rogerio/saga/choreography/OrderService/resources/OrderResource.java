package com.rogerio.saga.choreography.OrderService.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rogerio.saga.choreography.OrderService.models.request.OrderRequest;
import com.rogerio.saga.choreography.OrderService.services.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderResource {
	
	@Autowired
	OrderService orderService;

	@PostMapping(path="/create")
	public ResponseEntity<String> createOrder(@RequestBody OrderRequest request) {
		orderService.createOrder(request.getUser(),request.getTotal());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
		
	@GetMapping("approve")
	public String approveOrder() {
		// TODO
		return "Approving order";
	} 
	
	@GetMapping("reject")
	public String rejectOrder() {
		// TODO
		return "Rejecting order";
	} 
	
	
}
