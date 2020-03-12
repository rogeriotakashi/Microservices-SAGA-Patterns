package com.rogerio.StockService.models.responses;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rogerio.StockService.enums.ProcessStatusEnum;

import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "class")
public class ProcessOrderResponse {
	
	private Long orderId;
	private ProcessStatusEnum status;
	
	public ProcessOrderResponse(Long orderId, ProcessStatusEnum status) {
		this.orderId = orderId;
		this.status = status;
	}
	
	
}
