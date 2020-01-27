package com.rogerio.saga.choreography.OrderService.models.requests;

public class OrderRequest {
	
	private String user;
	private double total;
	
	public OrderRequest(String user, double total) {
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
