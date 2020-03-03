package com.rogerio.saga.orchestrator.OrchestratorService.models.requests.product;

import java.util.List;

import com.rogerio.saga.orchestrator.OrchestratorService.models.ProductDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class CalculateTotalRequest {
	
	@NonNull
	private List<ProductDTO> products;

	
}
