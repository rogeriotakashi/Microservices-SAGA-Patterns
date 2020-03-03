package com.rogerio.saga.choreography.OrderService.models.requests;

import lombok.Data;

@Data
public class ApproveOrderRequest {

	private Long orderId;

	public ApproveOrderRequest(Long orderId) {
		this.orderId = orderId;
	}

	
}
