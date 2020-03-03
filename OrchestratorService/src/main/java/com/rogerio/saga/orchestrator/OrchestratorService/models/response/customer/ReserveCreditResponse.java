package com.rogerio.saga.orchestrator.OrchestratorService.models.response.customer;


import com.rogerio.saga.orchestrator.OrchestratorService.enums.ReserveStatusEnum;

import lombok.Data;

@Data
public class ReserveCreditResponse {
	
	private ReserveStatusEnum status;

	public ReserveCreditResponse(ReserveStatusEnum status) {
		this.status = status;
	}
	
	
}
