package com.rogerio.saga.choreography.CustomerService.models;

import com.rogerio.saga.choreography.CustomerService.enums.ValidatorEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidatorResponse {

	private Long orderId;
	private ValidatorEnum status;
	private String serviceName;
	
	public ValidatorResponse(Long orderId, ValidatorEnum status, String serviceName) {
		this.orderId = orderId;
		this.status = status;
		this.serviceName = serviceName;
	}
	
	
	
	
}
