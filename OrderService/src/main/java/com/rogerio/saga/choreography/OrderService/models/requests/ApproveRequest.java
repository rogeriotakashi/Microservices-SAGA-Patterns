package com.rogerio.saga.choreography.OrderService.models.requests;

public class ApproveRequest {

	private Long orderId;

	public ApproveRequest() {
	}

	public ApproveRequest(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}
