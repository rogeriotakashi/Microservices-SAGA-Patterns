package com.rogerio.saga.orchestrator.OrchestratorService.enums;


public enum OrderStatusEnum {
	CREATED(1),
	PENDING(2),
	APPROVED(3),
	REJECTED(4),
	CANCELED(5), 
	NOT_FOUND(6), 
	DELETED(7),
	PROCESSED(8);
	
	private final int status;

	OrderStatusEnum(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}
	
	public static OrderStatusEnum fromInteger(int x) {
        switch(x) {
        case 1:
            return CREATED;
        case 2:
            return PENDING;
        case 3:
            return APPROVED;
        case 4: 	
            return REJECTED;
        case 5:
        	return CANCELED;
        case 6:
        	return NOT_FOUND;
        case 7:
        	return DELETED;
        case 8:
        	return PROCESSED;
            
        }
        return null;
    }
	
	
	
	
	
}
