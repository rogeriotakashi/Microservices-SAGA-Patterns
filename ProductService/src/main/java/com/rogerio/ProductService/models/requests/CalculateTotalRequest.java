package com.rogerio.ProductService.models.requests;

import java.util.List;

import com.rogerio.ProductService.models.ProductDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class CalculateTotalRequest {
	
	@NonNull
	private List<ProductDTO> products;

	
}
