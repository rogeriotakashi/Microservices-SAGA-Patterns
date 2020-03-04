package com.rogerio.saga.orchestrator.OrchestratorService.models.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rogerio.saga.orchestrator.OrchestratorService.models.OrderDTO;

import lombok.Data;

@Data
public class CreateOrderResponse {

	@JsonProperty("order")
	private OrderDTO orderDTO;
}
