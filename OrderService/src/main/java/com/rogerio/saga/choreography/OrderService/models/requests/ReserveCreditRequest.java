package com.rogerio.saga.choreography.OrderService.models.requests;

import lombok.Data;

@Data
public class ReserveCreditRequest {
	
	private String user;
	private double total;
	private Long orderId;
	
	public ReserveCreditRequest(String user, double total, Long orderId) {
		this.user = user;
		this.total = total;
		this.orderId = orderId;
	}
	
}
