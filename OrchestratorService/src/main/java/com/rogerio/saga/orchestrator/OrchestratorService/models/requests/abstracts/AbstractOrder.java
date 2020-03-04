package com.rogerio.saga.orchestrator.OrchestratorService.models.requests.abstracts;

import lombok.Data;

@Data
public abstract class AbstractOrder {
	private Long id;

	public AbstractOrder(Long id) {
		this.id = id;
	}
	
}
