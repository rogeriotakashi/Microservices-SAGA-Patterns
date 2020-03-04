package com.rogerio.saga.choreography.OrderService.models.response;


import com.rogerio.saga.choreography.OrderService.enums.OrderStatusEnum;
import com.rogerio.saga.choreography.OrderService.models.abstracts.AbstractOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ApproveOrderResponse extends AbstractOrder {
	
	private OrderStatusEnum status;

	public ApproveOrderResponse(Long id, OrderStatusEnum status) {
		super(id);
		this.status = status;
	}

}
