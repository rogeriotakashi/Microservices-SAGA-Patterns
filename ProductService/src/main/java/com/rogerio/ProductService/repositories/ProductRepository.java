package com.rogerio.ProductService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rogerio.ProductService.models.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

}
