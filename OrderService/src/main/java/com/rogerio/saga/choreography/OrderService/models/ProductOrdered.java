package com.rogerio.saga.choreography.OrderService.models;

import lombok.Data;

@Data
public class ProductOrdered {
	
	private Long id;
	private String name;
	private int quantity;

}
