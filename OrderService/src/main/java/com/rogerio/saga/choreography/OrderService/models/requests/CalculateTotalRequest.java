package com.rogerio.saga.choreography.OrderService.models.requests;

import java.util.List;

import com.rogerio.saga.choreography.OrderService.models.ProductOrdered;

public class CalculateTotalRequest {
	private List<ProductOrdered> productsOrdered;

	public CalculateTotalRequest() {}

	public CalculateTotalRequest(List<ProductOrdered> productsOrdered) {
		this.productsOrdered = productsOrdered;
	}

	public List<ProductOrdered> getProductsOrdered() {
		return productsOrdered;
	}

	public void setProductsOrdered(List<ProductOrdered> productsOrdered) {
		this.productsOrdered = productsOrdered;
	}
	
	
}
