package com.rogerio.saga.choreography.OrderService.models.requests;

public class OrderResultRequest {

	private Long orderId;

	public OrderResultRequest() {
	}

	public OrderResultRequest(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}
