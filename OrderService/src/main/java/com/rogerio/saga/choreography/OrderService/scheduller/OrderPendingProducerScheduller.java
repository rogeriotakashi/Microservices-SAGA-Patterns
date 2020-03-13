package com.rogerio.saga.choreography.OrderService.scheduller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rogerio.saga.choreography.OrderService.config.KafkaConfig;
import com.rogerio.saga.choreography.OrderService.enums.OrderStatusEnum;
import com.rogerio.saga.choreography.OrderService.models.Order;
import com.rogerio.saga.choreography.OrderService.repositories.OrderRepository;

@Service
public class OrderPendingProducerScheduller {

	@Autowired
	OrderRepository repo;
	
	@Autowired
	KafkaConfig kafkaConfig;
	
	@Autowired
	KafkaTemplate<String, List<Long>> kafkaTemplate;
	
	@Scheduled(fixedDelayString = "${app.scheduler.send-pending-orders}")
	public void sendPendingOrders() {
		
		Optional<List<Order>> pendingOrdersListOpt = repo.findAllByStatus(OrderStatusEnum.PENDING.getStatus());
		pendingOrdersListOpt.ifPresent(pendingOrdersList -> {
			List<Long> pendingOrdersIdList = pendingOrdersList.stream()
					.map(Order::getId)
					.collect(Collectors.toList());
			
			kafkaTemplate.send(kafkaConfig.getPendingOrdersTopic(), pendingOrdersIdList);
		});
	}
}
