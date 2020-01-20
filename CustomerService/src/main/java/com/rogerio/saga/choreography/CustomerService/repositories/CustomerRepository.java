package com.rogerio.saga.choreography.CustomerService.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rogerio.saga.choreography.CustomerService.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Optional<Customer> findByUsername(String username);
}
