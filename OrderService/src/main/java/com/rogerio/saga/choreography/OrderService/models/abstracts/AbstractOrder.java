package com.rogerio.saga.choreography.OrderService.models.abstracts;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class AbstractOrder {
	
	private Long orderId;
	
	public AbstractOrder(Long orderId) {
		this.orderId = orderId;
	}
	
}
