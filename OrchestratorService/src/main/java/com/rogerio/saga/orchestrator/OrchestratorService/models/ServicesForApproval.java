package com.rogerio.saga.orchestrator.OrchestratorService.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Services_For_Approval")
@Data
public class ServicesForApproval {

	@Id
	private Long id;
	private String serviceName;
	private String operation;
}
