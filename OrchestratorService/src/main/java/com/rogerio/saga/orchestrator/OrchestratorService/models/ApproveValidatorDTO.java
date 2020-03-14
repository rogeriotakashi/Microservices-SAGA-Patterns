package com.rogerio.saga.orchestrator.OrchestratorService.models;

import java.util.List;

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
