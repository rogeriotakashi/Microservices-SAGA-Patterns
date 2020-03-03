package com.rogerio.saga.orchestrator.OrchestratorService.models.requests.abstracts;

import lombok.Data;

@Data
public abstract class AbstractOrderRequest {
	private Long id;

	public AbstractOrderRequest(Long id) {
		this.id = id;
	}
	
}
