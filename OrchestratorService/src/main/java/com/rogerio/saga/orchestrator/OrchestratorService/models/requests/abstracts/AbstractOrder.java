package com.rogerio.saga.orchestrator.OrchestratorService.models.requests.abstracts;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class AbstractOrder {
	private Long id;

	public AbstractOrder(Long id) {
		this.id = id;
	}
	
}
