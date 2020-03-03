package com.rogerio.saga.choreography.OrderService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rogerio.saga.choreography.OrderService.models.ProductsOrdered;
import com.rogerio.saga.choreography.OrderService.models.requests.CalculateTotalRequest;
import com.rogerio.saga.choreography.OrderService.models.response.CalculateTotalResponse;

@Service
public class ProductService {
	
	@Autowired
	RestTemplate rest;

	public double calculateTotal(List<ProductsOrdered> productsOrdered) {
		CalculateTotalRequest calculateTotalRequest = new CalculateTotalRequest(productsOrdered);
		CalculateTotalResponse calculateTotalResponse = rest.postForObject("http://PRODUCT-SERVICE/api/v1/product/calculate-total", calculateTotalRequest, CalculateTotalResponse.class);
		return calculateTotalResponse.getTotal();
	}
}
