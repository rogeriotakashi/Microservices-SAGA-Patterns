package com.rogerio.saga.choreography.OrderService.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rogerio.saga.choreography.OrderService.models.Order;
import com.rogerio.saga.choreography.OrderService.models.requests.CalculateTotalRequest;
import com.rogerio.saga.choreography.OrderService.models.requests.OrderRequest;
import com.rogerio.saga.choreography.OrderService.models.requests.OrderResultRequest;
import com.rogerio.saga.choreography.OrderService.models.requests.ProcessOrderRequest;
import com.rogerio.saga.choreography.OrderService.models.requests.ReserveCreditRequest;
import com.rogerio.saga.choreography.OrderService.services.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderResource {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	RestTemplate rest;

	@PostMapping("/create")
	public ResponseEntity<String> createPendingOrder(@RequestBody OrderRequest request) {
		Order order = orderService.createOrder(request.getUser(),request.getTotal());
		
		// Calculate the total price
		CalculateTotalRequest calculateTotalRequest = new CalculateTotalRequest(request.getProductsOrdered());
		ResponseEntity<?> calculateTotalResponse = rest.postForEntity("http://PRODUCT-SERVICE/api/v1/product/calculate-total", calculateTotalRequest, HttpEntity.class);
		 // TODO: Sum the total		
		
		// Calling reserve-credit service
		ReserveCreditRequest reserveCreditRequest = new ReserveCreditRequest(order.getUser(), order.getTotal(), order.getId());
		ResponseEntity<?> reservCredtiResponse = rest.postForEntity("http://CUSTOMER-SERVICE/api/v1/customer/reserve-credit", reserveCreditRequest, HttpEntity.class);
		
		return new ResponseEntity<>(reservCredtiResponse.getStatusCode());
	}
		
	@PostMapping("/approve")
	public ResponseEntity<String> approveOrder(@RequestBody OrderResultRequest req) {
		orderService.approveOrder(req.getOrderId());		
		
		// Process ordered products
		ProcessOrderRequest processOrderRequest  = new ProcessOrderRequest();
		ResponseEntity<?> response = rest.postForEntity("http://STOCK-SERVICE/api/v1/stock/processOrder", processOrderRequest, HttpEntity.class);

				
		return new ResponseEntity<>(HttpStatus.OK);
	} 
	
	@PostMapping("/reject")
	public ResponseEntity<String> rejectOrder(@RequestBody OrderResultRequest req) {
		orderService.rejectOrder(req.getOrderId());		
		return new ResponseEntity<>(HttpStatus.OK);
	} 
	
	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<String> rejectOrder(@PathVariable(value="orderId") Long id) {
		orderService.deleteOrder(id);
		return new ResponseEntity<>(HttpStatus.OK);
	} 
	
	
}
