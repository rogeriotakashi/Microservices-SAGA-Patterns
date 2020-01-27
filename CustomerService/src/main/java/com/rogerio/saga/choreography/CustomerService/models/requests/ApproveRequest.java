package com.rogerio.saga.choreography.CustomerService.models.requests;

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
