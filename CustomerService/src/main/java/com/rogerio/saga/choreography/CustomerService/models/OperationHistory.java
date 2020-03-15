package com.rogerio.saga.choreography.CustomerService.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Operation_History")
@Data
@NoArgsConstructor
public class OperationHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long orderId;
	private String username;
	private String operationType;
	private double totalOperation;
	
	
	public OperationHistory(Long orderId, String username, double totalOperation) {
		this.orderId = orderId;
		this.username = username;
		this.totalOperation = totalOperation;
	}
	
	
}
