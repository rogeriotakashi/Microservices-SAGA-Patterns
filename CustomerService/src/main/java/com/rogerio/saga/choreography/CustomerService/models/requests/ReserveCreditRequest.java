package com.rogerio.saga.choreography.CustomerService.models.requests;

public class ReserveCreditRequest {
	private String user;
	private double total;
	
	
	public ReserveCreditRequest(String user, double total) {
		super();
		this.user = user;
		this.total = total;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
