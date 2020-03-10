package com.rogerio.saga.choreography.CustomerService.models.response;

import com.rogerio.saga.choreography.CustomerService.enums.ReserveStatusEnum;

import lombok.Data;

@Data
public class ReserveCreditResponse {
	
	private Long orderId;
	private ReserveStatusEnum status;

	public ReserveCreditResponse(Long orderId,ReserveStatusEnum status) {
		this.orderId = orderId;
		this.status = status;
	}
	
	
}
