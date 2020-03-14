package com.rogerio.saga.orchestrator.OrchestratorService.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rogerio.saga.orchestrator.OrchestratorService.models.dtos.OrderDTO;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.OrderRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.product.CalculateTotalResponse;
import com.rogerio.saga.orchestrator.OrchestratorService.services.CustomerService;
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
	CustomerService customerService;
	
	@Autowired
	StockService stockService;


	@PostMapping("/order")
	public ResponseEntity<String> order(@RequestBody OrderRequest req) {
		log.info("Entering /order endpoint. OrderRequest: {}", req);

		try {
			// Sync calls using REST calls
			CalculateTotalResponse totalResponse = productService.calculateTotal(req.getProducts());
			OrderDTO order = orderService.createOrder(req.getUser(), totalResponse.getTotal());

			// Async calls using Kafka
			customerService.reserveCredit(order.getId(), req.getUser(), totalResponse.getTotal());
			stockService.processOrder(order.getId(), req.getProducts());
	
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch(IllegalStateException e) {
			log.error("Problem calling service: " + e);
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	

}
