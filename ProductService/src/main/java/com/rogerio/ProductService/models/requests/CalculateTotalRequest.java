package com.rogerio.ProductService.models.requests;

import java.util.List;

import com.rogerio.ProductService.models.ProductOrdered;

import lombok.Data;

@Data
public class CalculateTotalRequest {
	
	private List<ProductOrdered> productsOrdered;

}
