package com.rogerio.saga.choreography.OrderService.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rogerio.saga.choreography.OrderService.models.Order;
import com.rogerio.saga.choreography.OrderService.models.requests.OrderRequest;
import com.rogerio.saga.choreography.OrderService.models.requests.OrderResultRequest;
import com.rogerio.saga.choreography.OrderService.services.CustomerService;
import com.rogerio.saga.choreography.OrderService.services.OrderService;
import com.rogerio.saga.choreography.OrderService.services.ProductService;
import com.rogerio.saga.choreography.OrderService.services.StockService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderResource {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	StockService stockService;
	

	@PostMapping("/create")
	public ResponseEntity<String> createPendingOrder(@RequestBody OrderRequest request) {
		
		double total = productService.calculateTotal(request.getProductsOrdered());
		Order order = orderService.createOrder(request.getUser(), total);
		ResponseEntity<?> reservCredtiResponse = customerService.reserveCredit(order);
		
		return new ResponseEntity<>(reservCredtiResponse.getStatusCode());
	}

	@PostMapping("/approve")
	public ResponseEntity<String> approveOrder(@RequestBody OrderResultRequest req) {
		
		orderService.approveOrder(req.getOrderId());		
		ResponseEntity<?> processOrderStatus = stockService.processOrder();
		
		return new ResponseEntity<>(processOrderStatus.getStatusCode());
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
