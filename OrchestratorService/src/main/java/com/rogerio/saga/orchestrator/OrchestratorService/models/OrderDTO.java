package com.rogerio.saga.orchestrator.OrchestratorService.models;

import lombok.Data;

@Data
public class OrderDTO {

	private Long id;
	private int status;
	private String user;
	private double total;
	
}
