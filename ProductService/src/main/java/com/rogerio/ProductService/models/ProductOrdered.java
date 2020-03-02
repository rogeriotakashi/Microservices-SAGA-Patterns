package com.rogerio.ProductService.models;

import lombok.Data;

@Data
public class ProductOrdered {
	
	private Long id;
	private String name;
	private int quantity;

}
