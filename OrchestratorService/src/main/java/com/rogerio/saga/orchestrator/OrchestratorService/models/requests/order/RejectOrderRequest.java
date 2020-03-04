package com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order;

import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.abstracts.AbstractOrder;

public class RejectOrderRequest extends AbstractOrder {

	public RejectOrderRequest(Long orderId) {
		super(orderId);
	}

}
