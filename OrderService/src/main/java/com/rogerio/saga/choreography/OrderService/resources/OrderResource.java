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

import com.rogerio.saga.choreography.OrderService.enums.OrderStatusEnum;
import com.rogerio.saga.choreography.OrderService.models.Order;
import com.rogerio.saga.choreography.OrderService.models.requests.ApproveOrderRequest;
import com.rogerio.saga.choreography.OrderService.models.requests.CreateOrderRequest;
import com.rogerio.saga.choreography.OrderService.models.requests.RejectOrderRequest;
import com.rogerio.saga.choreography.OrderService.models.response.ApproveOrderResponse;
import com.rogerio.saga.choreography.OrderService.models.response.CreateOrderResponse;
import com.rogerio.saga.choreography.OrderService.services.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderResource {
	
	@Autowired
	OrderService orderService;
		

	@PostMapping("/create")
	public ResponseEntity<CreateOrderResponse> createPendingOrder(@RequestBody CreateOrderRequest req) {	
		Order order = orderService.createOrder(req.getUser(), req.getTotal());
		CreateOrderResponse response = new CreateOrderResponse(order);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/approve")
	public ResponseEntity<ApproveOrderResponse> approveOrder(@RequestBody ApproveOrderRequest req) {	
		OrderStatusEnum status = orderService.approveOrder(req.getId());
		ApproveOrderResponse approveOrderResponse = new ApproveOrderResponse(req.getId(), status);
		return new ResponseEntity<>(approveOrderResponse, HttpStatus.OK);
	}

	@PostMapping("/reject")
	public ResponseEntity<String> rejectOrder(@RequestBody RejectOrderRequest req) {
		orderService.rejectOrder(req.getOrderId());	
		return new ResponseEntity<>(HttpStatus.OK);
	} 
	
	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<String> rejectOrder(@PathVariable(value="orderId") Long id) {	
		orderService.deleteOrder(id);
		return new ResponseEntity<>(HttpStatus.OK);
	} 
	
}
