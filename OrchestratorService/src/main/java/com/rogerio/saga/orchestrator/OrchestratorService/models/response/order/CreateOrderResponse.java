package com.rogerio.saga.orchestrator.OrchestratorService.models.response.order;

import com.rogerio.saga.orchestrator.OrchestratorService.models.OrderDTO;

import lombok.Data;

@Data
public class CreateOrderResponse {

	private OrderDTO orderDTO;
}
