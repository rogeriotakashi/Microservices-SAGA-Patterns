package com.rogerio.saga.orchestrator.OrchestratorService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rogerio.saga.orchestrator.OrchestratorService.models.dtos.ProductDTO;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.product.CalculateTotalRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.product.CalculateTotalResponse;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ProductService {
	
	@Autowired
	RestTemplate rest;

	public CalculateTotalResponse calculateTotal(List<ProductDTO> products){
		log.info("Entering calculateTotal method. List of ProductsDTO {}", products);
		
		log.info("Creating CalculateTotalRequest using the list of products and making a request to ProductService [/product/calculate-total]");
		CalculateTotalRequest calculateTotalRequest = new CalculateTotalRequest(products);		
		CalculateTotalResponse calculateTotalResponse = rest.postForObject("http://PRODUCT-SERVICE/api/v1/product/calculate-total", calculateTotalRequest, CalculateTotalResponse.class);
		
		log.info("Creating CalculateTotalResponse {}", calculateTotalResponse);
		return calculateTotalResponse;
	}
	
	
}
