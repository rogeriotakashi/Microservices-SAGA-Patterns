package com.rogerio.ProductService.models.response;

import lombok.Data;

@Data
public class CalculateTotalResponse {

	double total;

	public CalculateTotalResponse(double total) {
		this.total = total;
	}
	
}
