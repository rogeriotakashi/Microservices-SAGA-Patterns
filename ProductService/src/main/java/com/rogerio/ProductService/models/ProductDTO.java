package com.rogerio.ProductService.models;

import lombok.Data;

@Data
public class ProductDTO {
	
	private Long id;
	private String name;
	private int quantity;

}
