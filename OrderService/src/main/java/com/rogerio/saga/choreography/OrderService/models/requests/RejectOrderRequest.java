package com.rogerio.saga.choreography.OrderService.models.requests;

import lombok.Data;

@Data
public class RejectOrderRequest {

	private Long orderId;

}
