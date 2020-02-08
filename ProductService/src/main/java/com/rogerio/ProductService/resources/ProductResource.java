package com.rogerio.ProductService.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rogerio.ProductService.models.requests.CalculateTotalRequest;


@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {
	
	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/calculate-total")
	public ResponseEntity<String> calculateTotal(@RequestBody CalculateTotalRequest req){
		
		
		
		
		
		return null;
		
	}
}
