package com.rogerio.saga.orchestrator.OrchestratorService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rogerio.saga.orchestrator.OrchestratorService.models.ProductDTO;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.product.CalculateTotalRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.product.CalculateTotalResponse;

@Service
public class ProductService {
	
	@Autowired
	RestTemplate rest;

	public double calculateTotal(List<ProductDTO> products) {
		CalculateTotalRequest calculateTotalRequest = new CalculateTotalRequest(products);
		CalculateTotalResponse calculateTotalResponse = rest.postForObject("http://PRODUCT-SERVICE/api/v1/product/calculate-total", calculateTotalRequest, CalculateTotalResponse.class);
		return calculateTotalResponse.getTotal();
	}
}
