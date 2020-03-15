package com.rogerio.StockService.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rogerio.StockService.enums.ProcessStatusEnum;
import com.rogerio.StockService.exceptions.ProductStockNotAvailableException;
import com.rogerio.StockService.models.requests.ProcessOrderRequest;
import com.rogerio.StockService.services.StockService;

@RestController
@RequestMapping("/api/v1/stock")
public class StockResource {

	@Autowired
	StockService stockService;
	
	@PutMapping("/process")
	@Transactional(rollbackFor = {ProductStockNotAvailableException.class})
	public ResponseEntity<String> processOrder(@RequestBody ProcessOrderRequest req) throws ProductStockNotAvailableException {
		ProcessStatusEnum status = stockService.processOrder(req.getProductsOrdered());
		
		switch(status) {
			case SUCCESS:
				return new ResponseEntity<>(HttpStatus.OK);
			case PRODUCT_NOT_AVAILIBLE:
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			case PRODUCT_NOT_FOUND:
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
}
