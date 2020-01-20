package com.rogerio.saga.choreography.OrderService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rogerio.saga.choreography.OrderService.enums.OrderStatusEnum;
import com.rogerio.saga.choreography.OrderService.models.Order;
import com.rogerio.saga.choreography.OrderService.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;

	public void createOrder(String user, double total) {
		Order order = new Order();
		order.setUser(user);
		order.setTotal(total);
		order.setStatus(OrderStatusEnum.PENDING.getStatus());
		
		orderRepo.save(order);
	}
}
