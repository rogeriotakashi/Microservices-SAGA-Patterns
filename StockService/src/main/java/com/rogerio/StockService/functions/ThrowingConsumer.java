package com.rogerio.StockService.functions;

import java.util.function.Consumer;

import com.rogerio.StockService.exceptions.ProductStockNotAvailableException;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
	void accept(T t) throws E;
	
	// wrapper
	static <T> Consumer<T> wrap(ThrowingConsumer<T, ProductStockNotAvailableException> throwingConsumer){
		
		return i -> {
			try {
				throwingConsumer.accept(i);
			} catch (Exception e) {
				throw new ProductStockNotAvailableException("Product not available for order", e);
			}
		};
	}
}
