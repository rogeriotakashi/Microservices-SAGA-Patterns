package com.rogerio.saga.choreography.OrderService.models.requests;

import java.util.List;

import com.rogerio.saga.choreography.OrderService.models.ProductsOrdered;

import lombok.Data;

@Data
public class CalculateTotalRequest {
	
	private List<ProductsOrdered> productsOrdered;

	public CalculateTotalRequest(List<ProductsOrdered> productsOrdered) {
		this.productsOrdered = productsOrdered;
	}
	
}
