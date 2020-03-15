package com.rogerio.saga.choreography.CustomerService.models.dtos;

import lombok.Data;

@Data
public class ChargebackDTO {

	private String username;
	private double total;
	
	public ChargebackDTO(String username, double total) {
		this.username = username;
		this.total = total;
	}
	
	
}
