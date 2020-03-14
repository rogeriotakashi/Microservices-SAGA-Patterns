package com.rogerio.saga.choreography.CustomerService.models.response;


import com.rogerio.saga.choreography.CustomerService.enums.ValidatorEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidatorResponse {

	private Long orderId;
	private ValidatorEnum status;
	private String serviceName;
	private String methodName;
	
	public ValidatorResponse(Long orderId, ValidatorEnum status, String serviceName,String methodName) {
		this.orderId = orderId;
		this.status = status;
		this.serviceName = serviceName;
		this.methodName = methodName;
	}
	
	
	
	
}
