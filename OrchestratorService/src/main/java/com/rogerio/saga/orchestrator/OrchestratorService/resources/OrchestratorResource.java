package com.rogerio.saga.orchestrator.OrchestratorService.resources;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rogerio.saga.orchestrator.OrchestratorService.enums.OrderStatusEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.enums.ReserveStatusEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.models.OrderDTO;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.OrderRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.services.CustomerService;
import com.rogerio.saga.orchestrator.OrchestratorService.services.OrderService;
import com.rogerio.saga.orchestrator.OrchestratorService.services.ProductService;
import com.rogerio.saga.orchestrator.OrchestratorService.services.StockService;



@RestController
@RequestMapping(value = "api/v1")
public class OrchestratorResource {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	StockService stockService;
	
	@PostMapping("/order")
	public ResponseEntity<String> order(@RequestBody OrderRequest req) {
		
		// Calculate the total payment amount of the order
		double total = productService.calculateTotal(req.getProducts());
		
		// Create Order
		OrderDTO orderDTO = orderService.createOrder(req.getUser(), total);
		
		// Reserve Credit from User
		ReserveStatusEnum reserveStatus = customerService.reserveCredit(orderDTO);
		
		// Get next order action based on reserve status (approved,rejected or delete)
		Function<Long,OrderStatusEnum> nextOrderAction = getNextOrderActionByReserveStatus(reserveStatus);
		OrderStatusEnum orderStatus = nextOrderAction.apply(orderDTO.getId());
		
		// Get next order action based on reserve response
		Function<Long, OrderStatusEnum> nextOrderAction2 = getNextActionByActualOrderStatus(orderStatus);
		OrderStatusEnum orderStatus2 = nextOrderAction2.apply(orderDTO.getId());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private Function<Long,OrderStatusEnum> getNextOrderActionByReserveStatus(ReserveStatusEnum reserveStatus) {
		switch(reserveStatus) {	
			case RESERVED:
				return (T) -> orderService.approveOrder(T);
			case INSUFICIENT_CREDIT:
				return (T) -> orderService.rejectOrder(T);
			case CUSTOMER_NOT_FOUND:
				return (T) -> orderService.deleteOrder(T);
		}
		
		return null;
	}
	
	private Function<Long, OrderStatusEnum> getNextActionByActualOrderStatus(OrderStatusEnum orderStatus) {
		switch(orderStatus) {	
			case APPROVED:
				return (T) -> stockService.processOrder(T);
			case NOT_APPROVED:
				break;
		default:
			break;
		}
		
		return null;	
	}
}
