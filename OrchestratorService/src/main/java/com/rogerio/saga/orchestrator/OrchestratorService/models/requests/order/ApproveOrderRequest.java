package com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order;

import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.abstracts.AbstractOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ApproveOrderRequest extends AbstractOrder {
	
	public ApproveOrderRequest(Long orderId) {
		super(orderId);
	}
	
}
