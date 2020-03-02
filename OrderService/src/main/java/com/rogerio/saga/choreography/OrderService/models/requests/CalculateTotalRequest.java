package com.rogerio.saga.choreography.OrderService.models.requests;

import java.util.List;

import com.rogerio.saga.choreography.OrderService.models.ProductOrdered;

import lombok.Data;

@Data
public class CalculateTotalRequest {
	
	private List<ProductOrdered> productsOrdered;

	public CalculateTotalRequest(List<ProductOrdered> productsOrdered) {
		this.productsOrdered = productsOrdered;
	}
	
}
