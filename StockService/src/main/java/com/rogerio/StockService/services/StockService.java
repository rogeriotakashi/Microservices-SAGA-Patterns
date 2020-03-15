package com.rogerio.StockService.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rogerio.StockService.enums.ProcessStatusEnum;
import com.rogerio.StockService.exceptions.ProductStockNotAvailableException;
import com.rogerio.StockService.functions.ThrowingConsumer;
import com.rogerio.StockService.models.ProductOrdered;
import com.rogerio.StockService.models.Stock;
import com.rogerio.StockService.repositories.StockRepository;

@Service
public class StockService {

	@Autowired
	public StockRepository stockRepo;

	public ProcessStatusEnum processOrder(List<ProductOrdered> productsOrdered) {
		try {
			productsOrdered.forEach(ThrowingConsumer.wrap(productOrdered -> {
				Optional<Stock> productStockOpt = stockRepo.findByProductId(productOrdered.getId());
				productStockOpt.ifPresent(ThrowingConsumer.wrap((stock -> {
					if (stock.getQuantity() - productOrdered.getQuantity() < 0)
						throw new ProductStockNotAvailableException();
					stock.setQuantity(stock.getQuantity() - productOrdered.getQuantity());
					stockRepo.save(stock);
				})));
			}));

			return ProcessStatusEnum.SUCCESS;
		} catch (ProductStockNotAvailableException e) {
			return ProcessStatusEnum.PRODUCT_NOT_AVAILIBLE;
		}
	}
}