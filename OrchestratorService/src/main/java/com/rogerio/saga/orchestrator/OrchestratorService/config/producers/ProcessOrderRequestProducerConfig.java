package com.rogerio.saga.orchestrator.OrchestratorService.config.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.rogerio.saga.orchestrator.OrchestratorService.config.producers.factory.ProducerConfigFactory;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.stock.ProcessOrderRequest;

@Configuration
public class ProcessOrderRequestProducerConfig {

	@Autowired
	ProducerConfigFactory<String, ProcessOrderRequest> factory;
	
	@Bean
	public ProducerFactory<String, ProcessOrderRequest> processOrderProducerFactory(){
		return factory.createDefaultProducerFactory();
	}
	
	@Bean
	public KafkaTemplate<String, ProcessOrderRequest> processOrdekafkaTemplate() {
        return factory.createDefaultkafkaTemplate();
    }
}
