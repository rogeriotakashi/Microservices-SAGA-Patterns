package com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order;

import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.abstracts.AbstractOrder;

public class ApproveOrderRequest extends AbstractOrder {

	public ApproveOrderRequest(Long orderId) {
		super(orderId);
	}
	
}
