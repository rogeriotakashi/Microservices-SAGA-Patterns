package com.rogerio.saga.orchestrator.OrchestratorService.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rogerio.saga.orchestrator.OrchestratorService.models.OrderServiceStatus;

@Repository
public interface OrderServicesStatusRepository extends JpaRepository<OrderServiceStatus, Long> {

	public Optional<List<OrderServiceStatus>> findByOrderId(Long orderId);
}
