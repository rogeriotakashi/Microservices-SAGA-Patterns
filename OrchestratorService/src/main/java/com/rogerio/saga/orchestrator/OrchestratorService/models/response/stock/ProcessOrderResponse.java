package com.rogerio.saga.orchestrator.OrchestratorService.models.response.stock;


import com.rogerio.saga.orchestrator.OrchestratorService.enums.ProcessStatusEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProcessOrderResponse {
	
	private Long orderId;
	private ProcessStatusEnum status;
	
	public ProcessOrderResponse(Long orderId, ProcessStatusEnum status) {
		this.orderId = orderId;
		this.status = status;
	}
	
	
}
