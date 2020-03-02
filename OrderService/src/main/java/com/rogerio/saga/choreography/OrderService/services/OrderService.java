package com.rogerio.saga.choreography.OrderService.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rogerio.saga.choreography.OrderService.enums.OrderStatusEnum;
import com.rogerio.saga.choreography.OrderService.models.Order;
import com.rogerio.saga.choreography.OrderService.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;

	public Order createOrder(String user, double total) {
		Order order = new Order();;
		order.setUser(user);
		order.setTotal(total);
		order.setStatus(OrderStatusEnum.PENDING.getStatus());
		
		
		
		return orderRepo.save(order);
	}

	public void approveOrder(Long orderId) {
		Optional<Order> order = orderRepo.findById(orderId);
		if(order.isPresent()) {
			order.get().setStatus(OrderStatusEnum.APPROVED.getStatus());
			orderRepo.saveAndFlush(order.get());
		}
		
	}

	public void rejectOrder(Long orderId) {
		Optional<Order> order = orderRepo.findById(orderId);
		if(order.isPresent()) {
			order.get().setStatus(OrderStatusEnum.REJECTED.getStatus());
			orderRepo.saveAndFlush(order.get());
		}
	}
	
	public void deleteOrder(Long id) {
		orderRepo.deleteById(id);
	}
}
