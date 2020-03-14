package com.rogerio.saga.orchestrator.OrchestratorService.models.dtos;

import java.util.List;

import com.rogerio.saga.orchestrator.OrchestratorService.models.OrderServiceStatus;

import lombok.Data;

@Data
public class ApproveValidatorDTO {

	private boolean isApproved;
	private OrderServiceStatus failedService;
	
	
	
	public ApproveValidatorDTO(boolean isApproved, OrderServiceStatus failedService) {
		this.isApproved = isApproved;
		this.failedService = failedService;
	}


	public ApproveValidatorDTO(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	
}
