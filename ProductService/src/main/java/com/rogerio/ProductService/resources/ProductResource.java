package com.rogerio.ProductService.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rogerio.ProductService.models.requests.CalculateTotalRequest;
import com.rogerio.ProductService.models.response.CalculateTotalResponse;
import com.rogerio.ProductService.service.ProductsService;


@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ProductsService productService;

	@PostMapping("/calculate-total")
	public ResponseEntity<CalculateTotalResponse> calculateTotal(@RequestBody CalculateTotalRequest req){
		
		if(req.getProductsOrdered() == null || req.getProductsOrdered().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		double total = productService.calculateTotal(req.getProductsOrdered());
		CalculateTotalResponse response = new CalculateTotalResponse();
		response.setTotal(total);
				
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
}
