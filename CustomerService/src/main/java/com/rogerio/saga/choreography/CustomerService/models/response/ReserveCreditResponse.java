package com.rogerio.saga.choreography.CustomerService.models.response;

import com.rogerio.saga.choreography.CustomerService.enums.ReserveStatusEnum;

import lombok.Data;

@Data
public class ReserveCreditResponse {
	
	private ReserveStatusEnum status;

	public ReserveCreditResponse(ReserveStatusEnum status) {
		this.status = status;
	}
	
	
}
