package com.rogerio.saga.orchestrator.OrchestratorService.models.response.order;

import com.rogerio.saga.orchestrator.OrchestratorService.enums.OrderStatusEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.abstracts.AbstractOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ApproveOrderResponse extends AbstractOrder {
	
	private OrderStatusEnum status;

	public ApproveOrderResponse(Long id, OrderStatusEnum status) {
		super(id);
		this.status = status;
	}

}
