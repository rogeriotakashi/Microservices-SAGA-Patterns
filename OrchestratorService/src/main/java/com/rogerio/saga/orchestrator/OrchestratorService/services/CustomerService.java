package com.rogerio.saga.orchestrator.OrchestratorService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rogerio.saga.orchestrator.OrchestratorService.enums.ReserveStatusEnum;
import com.rogerio.saga.orchestrator.OrchestratorService.models.OrderDTO;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.customer.ReserveCreditRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.customer.ReserveCreditResponse;


@Service
public class CustomerService {
	
	@Autowired
	RestTemplate rest;
	
	public ReserveStatusEnum reserveCredit(OrderDTO orderDTO) {
		ReserveCreditRequest reserveCreditRequest = new ReserveCreditRequest(orderDTO.getUser(), orderDTO.getTotal(), orderDTO.getId());
		ReserveCreditResponse reserveCreditResponse = rest.postForObject("http://CUSTOMER-SERVICE/api/v1/customer/reserve-credit", reserveCreditRequest, ReserveCreditResponse.class);
		return reserveCreditResponse.getStatus();
	}
}
