package com.rogerio.saga.choreography.CustomerService.models.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rogerio.saga.choreography.CustomerService.enums.ReserveStatusEnum;

import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "class")
public class ReserveCreditResponse {
	
	private Long orderId;
	private ReserveStatusEnum status;

	public ReserveCreditResponse(Long orderId,ReserveStatusEnum status) {
		this.orderId = orderId;
		this.status = status;
	}
	
	
}
