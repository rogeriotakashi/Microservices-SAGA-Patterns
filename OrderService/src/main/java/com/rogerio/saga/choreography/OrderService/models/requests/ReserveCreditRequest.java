package com.rogerio.saga.choreography.OrderService.models.requests;

public class ReserveCreditRequest {
	
	private String user;
	private double total;
	private Long orderId;
	
	public ReserveCreditRequest() {	}
	
	public ReserveCreditRequest(String user, double total, Long orderId) {
		super();
		this.user = user;
		this.total = total;
		this.orderId = orderId;
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
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	
}
