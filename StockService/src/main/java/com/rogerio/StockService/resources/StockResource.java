package com.rogerio.StockService.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rogerio.StockService.enums.ProcessStatusEnum;
import com.rogerio.StockService.exceptions.ProductStockNotAvailibleException;
import com.rogerio.StockService.models.requests.ProcessOrderRequest;
import com.rogerio.StockService.services.StockService;

@RestController
@RequestMapping("/api/v1/stock")
public class StockResource {

	@Autowired
	StockService stockService;
	
	@PostMapping("/process")
	@Transactional(rollbackFor = {ProductStockNotAvailibleException.class})
	public ResponseEntity<String> processOrder(@RequestBody ProcessOrderRequest req) throws ProductStockNotAvailibleException {
		ProcessStatusEnum status = stockService.processOrder(req.getProductsOrdered());
		
		switch(status) {
			case SUCCESS:
				return new ResponseEntity<>(HttpStatus.OK);
			case PRODUCT_NOT_AVAILIBLE:
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			case PRODUCT_NOT_FOUND:
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
}
