package com.rogerio.saga.choreography.OrderService.models.requests;


import com.rogerio.saga.choreography.OrderService.enums.OrderStatusEnum;
import com.rogerio.saga.choreography.OrderService.models.abstracts.AbstractOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class UpdateOrderStatusRequest extends AbstractOrder {
	
	OrderStatusEnum status;
	
	public UpdateOrderStatusRequest(Long orderId, OrderStatusEnum status) {
		super(orderId);
		this.status = status;
	}
	
}
