package com.rogerio.saga.orchestrator.OrchestratorService.models.requests.customer;

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
