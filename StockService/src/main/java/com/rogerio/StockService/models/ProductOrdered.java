package com.rogerio.StockService.models;

import lombok.Data;

@Data
public class ProductOrdered {
	
	private Long id;
	private String name;
	private int quantity;
	
}
