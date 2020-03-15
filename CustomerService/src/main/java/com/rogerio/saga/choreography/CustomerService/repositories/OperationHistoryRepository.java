package com.rogerio.saga.choreography.CustomerService.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rogerio.saga.choreography.CustomerService.models.OperationHistory;

@Repository
public interface OperationHistoryRepository extends JpaRepository<OperationHistory, Long> {

	Optional<OperationHistory> findByOrderId(Long orderId);
	Optional<String> findUsernameByOrderId(Long orderId);
}
