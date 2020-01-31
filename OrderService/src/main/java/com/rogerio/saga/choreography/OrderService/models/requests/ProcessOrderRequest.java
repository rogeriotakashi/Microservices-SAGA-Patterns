package com.rogerio.saga.choreography.OrderService.models.requests;

import java.util.List;

import com.rogerio.saga.choreography.OrderService.models.ProductOrdered;

public class ProcessOrderRequest {
	
	private Long orderId;
	
	private List<ProductOrdered> productsOrdered;

	public ProcessOrderRequest() {}

	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public List<ProductOrdered> getProductsOrdered() {
		return productsOrdered;
	}

	public void setProductsOrdered(List<ProductOrdered> productsOrdered) {
		this.productsOrdered = productsOrdered;
	}

	
	
}