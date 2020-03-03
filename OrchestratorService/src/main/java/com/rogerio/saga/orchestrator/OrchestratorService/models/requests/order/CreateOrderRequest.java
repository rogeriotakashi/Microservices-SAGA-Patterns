package com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order;

import lombok.Data;

@Data
public class CreateOrderRequest {

	private String user;
	private double total;
	
	public CreateOrderRequest(String user, double total) {
		this.user = user;
		this.total = total;
	}
	
	
}
