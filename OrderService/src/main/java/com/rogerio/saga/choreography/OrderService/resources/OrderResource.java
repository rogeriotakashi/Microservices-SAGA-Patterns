package com.rogerio.saga.choreography.OrderService.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderResource {

	@GetMapping
	public String createOrder() {
		// TODO
		return "Creating Order";
	}
	
	@GetMapping("pending")
	public String createPendingOrder() {
		// TODO
		return "Setting pending status to Order";
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
