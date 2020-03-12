package com.rogerio.saga.orchestrator.OrchestratorService.models.requests.abstracts;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class AbstractOrder {
	private Long orderId;

	public AbstractOrder(Long orderId) {
		this.orderId = orderId;
	}
	
}
