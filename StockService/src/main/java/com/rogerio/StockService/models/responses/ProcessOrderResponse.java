package com.rogerio.StockService.models.responses;

import com.rogerio.StockService.enums.ProcessStatusEnum;

import lombok.Data;

@Data
public class ProcessOrderResponse {
	
	private Long orderId;
	private ProcessStatusEnum status;
	
	public ProcessOrderResponse(Long orderId, ProcessStatusEnum status) {
		this.orderId = orderId;
		this.status = status;
	}
	
	
}
