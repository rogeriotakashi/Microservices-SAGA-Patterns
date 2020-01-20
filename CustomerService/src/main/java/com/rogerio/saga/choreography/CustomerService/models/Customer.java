package com.rogerio.saga.choreography.CustomerService.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String username;
	
	@NotNull
	private String name;
	
	@NotNull
	private double totalAvailible;
	
	
	
	public Customer() {}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getTotalAvailible() {
		return totalAvailible;
	}
	public void setTotalAvailible(double totalAvailible) {
		this.totalAvailible = totalAvailible;
	}

	
}
