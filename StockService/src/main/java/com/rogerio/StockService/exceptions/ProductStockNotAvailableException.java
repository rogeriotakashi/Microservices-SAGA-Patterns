package com.rogerio.StockService.exceptions;

public class ProductStockNotAvailableException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ProductStockNotAvailableException (String message, Throwable cause) {
		super(message,cause);
	}

	public ProductStockNotAvailableException() {
	}
	
	

}
