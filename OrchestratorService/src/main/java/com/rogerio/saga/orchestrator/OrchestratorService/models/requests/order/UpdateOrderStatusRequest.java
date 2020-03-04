package com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order;

import com.rogerio.saga.orchestrator.OrchestratorService.enums.OrderStatusEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.abstracts.AbstractOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class UpdateOrderStatusRequest extends AbstractOrder {
	
	OrderStatusEnum status;
	
	public UpdateOrderStatusRequest(Long orderId, OrderStatusEnum status) {
		super(orderId);
		this.status = status;
	}
	
}
