package com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order;

import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.abstracts.AbstractOrderRequest;

public class ApproveOrderRequest extends AbstractOrderRequest {

	public ApproveOrderRequest(Long orderId) {
		super(orderId);
	}
	
}
