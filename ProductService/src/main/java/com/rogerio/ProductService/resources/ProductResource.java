package com.rogerio.ProductService.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rogerio.ProductService.models.requests.CalculateTotalRequest;
import com.rogerio.ProductService.models.response.CalculateTotalResponse;
import com.rogerio.ProductService.service.ProductsService;


@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductResource.class);
	
	@Autowired
	ProductsService productService;

	@PostMapping("/calculate-total")
	public ResponseEntity<CalculateTotalResponse> calculateTotal(@RequestBody CalculateTotalRequest req) throws InterruptedException{
		logger.info("Incoming request for calculate-total");
		if(req.getProducts() == null || req.getProducts().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		double total = productService.calculateTotal(req.getProducts());
		CalculateTotalResponse response = new CalculateTotalResponse(total);
				
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
}
