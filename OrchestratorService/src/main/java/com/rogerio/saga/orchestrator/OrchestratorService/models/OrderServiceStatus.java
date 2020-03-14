package com.rogerio.saga.orchestrator.OrchestratorService.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.rogerio.saga.orchestrator.OrchestratorService.enums.ValidatorEnum;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity(name = "Order_Services_Status")
public class OrderServiceStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long orderId;
	private String status;
	private String serviceName;
	
	public OrderServiceStatus(Long orderId, ValidatorEnum status, String serviceName) {
		this.orderId = orderId;
		this.status = status.toString();
		this.serviceName = serviceName;
	}
	
	  
	  
	  
}
