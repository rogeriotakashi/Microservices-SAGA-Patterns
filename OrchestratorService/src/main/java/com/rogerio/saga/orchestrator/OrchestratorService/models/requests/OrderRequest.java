package com.rogerio.saga.orchestrator.OrchestratorService.models.requests;

import java.util.List;

import com.rogerio.saga.orchestrator.OrchestratorService.models.dtos.ProductDTO;

import lombok.Data;

@Data
public class OrderRequest {
	
	private String user;
	private List<ProductDTO> products;
	
}
