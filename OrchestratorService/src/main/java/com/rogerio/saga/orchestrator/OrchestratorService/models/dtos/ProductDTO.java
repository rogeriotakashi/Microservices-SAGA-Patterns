package com.rogerio.saga.orchestrator.OrchestratorService.models.dtos;

import lombok.Data;

@Data
public class ProductDTO {
	
	private Long id;
	private String name;
	private int quantity;

}
