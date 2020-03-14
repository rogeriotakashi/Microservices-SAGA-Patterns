package com.rogerio.saga.orchestrator.OrchestratorService.models.requests.stock;

import java.util.List;

import com.rogerio.saga.orchestrator.OrchestratorService.models.dtos.ProductDTO;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.abstracts.AbstractOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProcessOrderRequest extends AbstractOrder {
	
	@NonNull
	private List<ProductDTO> productsOrdered;

	public ProcessOrderRequest(Long id, @NonNull List<ProductDTO> productsOrdered) {
		super(id);
		this.productsOrdered = productsOrdered;
	}
	

	
}
