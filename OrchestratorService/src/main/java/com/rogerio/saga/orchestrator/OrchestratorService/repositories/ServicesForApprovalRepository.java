package com.rogerio.saga.orchestrator.OrchestratorService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rogerio.saga.orchestrator.OrchestratorService.models.ServicesForApproval;

@Repository
public interface ServicesForApprovalRepository extends JpaRepository<ServicesForApproval, Long> {
	
}
