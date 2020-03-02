package com.rogerio.saga.choreography.OrderService.models.requests;

import java.util.List;

import com.rogerio.saga.choreography.OrderService.models.ProductOrdered;

import lombok.Data;

@Data
public class ProcessOrderRequest {
	
	private Long orderId;
	private List<ProductOrdered> productsOrdered;

}
