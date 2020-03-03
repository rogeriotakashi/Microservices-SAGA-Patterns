package com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order;

import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.abstracts.AbstractOrderRequest;

public class RejectOrderRequest extends AbstractOrderRequest {

	public RejectOrderRequest(Long orderId) {
		super(orderId);
	}

}
