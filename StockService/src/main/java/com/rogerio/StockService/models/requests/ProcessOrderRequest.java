package com.rogerio.StockService.models.requests;

import java.util.List;

import com.rogerio.StockService.models.ProductOrdered;

import lombok.Data;

@Data
public class ProcessOrderRequest {
	
	private Long orderId;
	private List<ProductOrdered> productsOrdered;
	
}
