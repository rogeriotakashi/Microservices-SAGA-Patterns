package com.rogerio.saga.choreography.CustomerService.models.requests;

import lombok.Data;

@Data
public class ReserveCreditRequest {
	
	private String user;
	private double total;
	private Long orderId;
	
}
