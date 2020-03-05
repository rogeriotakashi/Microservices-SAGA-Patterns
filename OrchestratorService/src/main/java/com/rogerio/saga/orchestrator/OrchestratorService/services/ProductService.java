package com.rogerio.saga.orchestrator.OrchestratorService.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rogerio.saga.orchestrator.OrchestratorService.models.ProductDTO;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.product.CalculateTotalRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.product.CalculateTotalResponse;


@Service
public class ProductService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	RestTemplate rest;

	//@HystrixCommand(fallbackMethod = "fallback_calculateTotal")
	public CalculateTotalResponse calculateTotal(List<ProductDTO> products){
		logger.info("Calling calculate-total service from ProductService");
		CalculateTotalRequest calculateTotalRequest = new CalculateTotalRequest(products);
		CalculateTotalResponse calculateTotalResponse = rest.postForObject("http://PRODUCT-SERVICE/api/v1/product/calculate-total", calculateTotalRequest, CalculateTotalResponse.class);
		return calculateTotalResponse;
	}
	
	/*
	public CalculateTotalResponse fallback_calculateTotal(List<ProductDTO> products) throws ProductServiceException{
		throw new ProductServiceException("Error calling service calculate-total from ProductService"); 
	}
	*/
	
	
}
