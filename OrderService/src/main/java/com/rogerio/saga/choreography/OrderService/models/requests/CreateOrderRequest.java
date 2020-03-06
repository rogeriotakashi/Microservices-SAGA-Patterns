package com.rogerio.saga.choreography.OrderService.models.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateOrderRequest {

	private String user;
	private double total;
	
	public CreateOrderRequest(String user, double total) {
		this.user = user;
		this.total = total;
	}
	
	
}
