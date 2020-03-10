package com.rogerio.saga.orchestrator.OrchestratorService.config.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.rogerio.saga.orchestrator.OrchestratorService.config.producers.factory.ProducerConfigFactory;

@Configuration
public class ApproveOrderProducerConfig {

	@Autowired
	ProducerConfigFactory<String, Long> factory;
	
	@Bean
	public ProducerFactory<String, Long> approveOrderProducerFactory(){
		return factory.createOrderStatusProducerFactory();
	}
	
	@Bean
	public KafkaTemplate<String, Long> approveOrdekafkaTemplate() {
        return factory.createOrderStatuskafkaTemplate();
    }
	
}
