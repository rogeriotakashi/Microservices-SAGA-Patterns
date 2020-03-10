package com.rogerio.saga.orchestrator.OrchestratorService.resources;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rogerio.saga.orchestrator.OrchestratorService.enums.OrderStatusEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.enums.ReserveStatusEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.OrderRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.product.CalculateTotalResponse;
import com.rogerio.saga.orchestrator.OrchestratorService.services.OrderService;
import com.rogerio.saga.orchestrator.OrchestratorService.services.ProductService;
import com.rogerio.saga.orchestrator.OrchestratorService.services.StockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/v1")
@Slf4j
public class OrchestratorResource {
	
	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	

	@Autowired
	StockService stockService;

	@PostMapping("/order")
	public ResponseEntity<String> order(@RequestBody OrderRequest req) {
		log.info("Entering /order endpoint. OrderRequest: {}", req);

		try {
			CalculateTotalResponse total = productService.calculateTotal(req.getProducts());
			orderService.createOrder(req.getUser(), total.getTotal());
	
			
	
			// Get next order action based on reserve status (approved,rejected or delete)
			// Function<Long, OrderStatusEnum> nextOrderAction = getNextOrderActionByReserveStatus(reserveStatus);
			// OrderStatusEnum orderStatus = nextOrderAction.apply(orderDTO.getId());
	
			// Get next order action based on reserve response
			// if (orderStatus == OrderStatusEnum.APPROVED) {
			//	OrderStatusEnum orderStatusAfterProcess = stockService.processOrder(orderDTO.getId(), req.getProducts());
	
				// Update Order status after stock process
			//	orderService.updateOrderStatus(orderDTO.getId(), orderStatusAfterProcess);
			//}
	
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch(IllegalStateException e) {
			log.error("Problem calling service: " + e);
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	

}
