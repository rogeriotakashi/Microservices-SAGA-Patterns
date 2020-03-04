package com.rogerio.saga.orchestrator.OrchestratorService.enums;

public enum OrderStatusEnum {
	CREATED(1),
	PENDING(2),
	APPROVED(3),
	REJECTED(4),
	CANCELED(5), 
	NOT_APPROVED(6), 
	DELETED(7);
	
	private final int status;

	OrderStatusEnum(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}
	
	
	
	
}
