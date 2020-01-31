package com.rogerio.StockService.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rogerio.StockService.models.Stock;


public interface StockRepository extends JpaRepository<Stock, Long> {

	public Optional<Stock> findByProductId(Long productId);
}
