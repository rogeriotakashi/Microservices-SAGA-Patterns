package com.rogerio.saga.choreography.CustomerService.models.requests;

import lombok.Data;

@Data
public class OrderResultRequest {

	private Long orderId;

	public OrderResultRequest(Long orderId) {
		this.orderId = orderId;
	}

	
}
