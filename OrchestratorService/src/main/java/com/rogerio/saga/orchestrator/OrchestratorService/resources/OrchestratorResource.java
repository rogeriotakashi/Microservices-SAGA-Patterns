package com.rogerio.saga.orchestrator.OrchestratorService.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		
		switch(reserveStatus) {	
			case RESERVED:
				ResponseEntity<?> approveOrderStatus = orderService.approveOrder(orderDTO.getId());
				ResponseEntity<?> processOrderStatus = stockService.processOrder();	
				break;
			case INSUFICIENT_CREDIT:
				orderService.rejectOrder(orderDTO.getId());
				break;
			case CUSTOMER_NOT_FOUND:
				orderService.deleteOrder(orderDTO.getId());			
		}	
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
