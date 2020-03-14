package com.rogerio.saga.orchestrator.OrchestratorService.models.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {

	private Long id;
	private int status;
	private String user;
	private double total;
	
}
