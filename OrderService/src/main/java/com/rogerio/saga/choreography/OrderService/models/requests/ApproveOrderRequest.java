package com.rogerio.saga.choreography.OrderService.models.requests;

import com.rogerio.saga.choreography.OrderService.models.abstracts.AbstractOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ApproveOrderRequest extends AbstractOrder {

	public ApproveOrderRequest(Long orderId) {
		super(orderId);
	}
	
}
