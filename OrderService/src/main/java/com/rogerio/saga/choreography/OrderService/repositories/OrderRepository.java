package com.rogerio.saga.choreography.OrderService.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rogerio.saga.choreography.OrderService.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<List<Order>> findAllByStatus(int status);
}
