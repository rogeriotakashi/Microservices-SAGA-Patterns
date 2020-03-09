package com.rogerio.saga.orchestrator.OrchestratorService.config.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.rogerio.saga.orchestrator.OrchestratorService.config.producers.factory.ProducerConfigFactory;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.customer.ReserveCreditRequest;

@Configuration
public class ReserveCreditRequestProducerConfig {
	
	@Autowired
	private ProducerConfigFactory<String, ReserveCreditRequest> factory;
	
	
	@Bean
	public ProducerFactory<String, ReserveCreditRequest> createReserveCreditProducerFactory(){
		return factory.createDefaultProducerFactory();
	}
	
	@Bean
	public KafkaTemplate<String, ReserveCreditRequest> createReserveCreditkafkaTemplate() {
        return factory.createDefaultkafkaTemplate();
    }
}
