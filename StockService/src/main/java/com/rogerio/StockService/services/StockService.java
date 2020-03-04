package com.rogerio.StockService.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rogerio.StockService.enums.ProcessStatusEnum;
import com.rogerio.StockService.exceptions.ProductStockNotAvailibleException;
import com.rogerio.StockService.models.ProductOrdered;
import com.rogerio.StockService.models.Stock;
import com.rogerio.StockService.repositories.StockRepository;

@Service
public class StockService {

	@Autowired
	public StockRepository stockRepo;
	
	
	
	public ProcessStatusEnum processOrder(List<ProductOrdered> productsOrdered) throws ProductStockNotAvailibleException{
		boolean isAllProductsAvalible = productsOrdered.stream()
		.anyMatch(productOrdered -> productOrdered.getQuantity() > 0);
		
		if(isAllProductsAvalible) {
			productsOrdered.forEach( productOrdered -> processProduct(productOrdered.getId()));
			return ProcessStatusEnum.SUCCESS;
		} else {
			return ProcessStatusEnum.PRODUCT_NOT_AVAILIBLE;
		}
	}

	private void processProduct(Long productId) {
		Optional<Stock> productStockOpt = stockRepo.findByProductId(productId);
		productStockOpt.ifPresent((product) -> {
			product.setQuantity(product.getQuantity() - 1);
			stockRepo.save(product);
		});
	}
}
