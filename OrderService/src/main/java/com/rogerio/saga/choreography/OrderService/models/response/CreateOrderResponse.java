package com.rogerio.saga.choreography.OrderService.models.response;

import com.rogerio.saga.choreography.OrderService.models.Order;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreateOrderResponse {

	@NonNull
	private Order order;
}
