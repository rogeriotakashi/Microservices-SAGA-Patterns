package com.rogerio.saga.orchestrator.OrchestratorService.config.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.rogerio.saga.orchestrator.OrchestratorService.config.producers.factory.ProducerConfigFactory;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order.CreateOrderRequest;



@Configuration
public class CreateOrderRequestProducerConfig {
	
	@Autowired
	private ProducerConfigFactory<String, CreateOrderRequest> factory;
	
	
	@Bean
	public ProducerFactory<String, CreateOrderRequest> createOrderProducerFactory(){
		return factory.createDefaultProducerFactory();
	}
	
	@Bean
	public KafkaTemplate<String, CreateOrderRequest> createOrderkafkaTemplate() {
        return factory.createDefaultkafkaTemplate();
    }

	
	
}
