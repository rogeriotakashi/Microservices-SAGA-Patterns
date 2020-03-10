package com.rogerio.saga.orchestrator.OrchestratorService.config.consumers;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.rogerio.saga.orchestrator.OrchestratorService.config.consumers.factory.ConsumerConfigFactory;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.order.CreateOrderResponse;


@Configuration
@EnableKafka
public class CreateOrderResponseConsumerConfig {
	
	@Value("${app.consumers.groupid.create-order-response}")
	private String CREATE_ORDER_RESPONSE_GROUP;

	@Autowired
	ConsumerConfigFactory<String, CreateOrderResponse> factory;
	
    @Bean
    public DefaultKafkaConsumerFactory<String, CreateOrderResponse> orderConsumerFactory() {
    	DefaultKafkaConsumerFactory<String, CreateOrderResponse> cf = factory.createDefaultConsumerFactory();
    	cf.getConfigurationProperties().put(ConsumerConfig.GROUP_ID_CONFIG, CREATE_ORDER_RESPONSE_GROUP);
    	cf.setKeyDeserializer(new StringDeserializer());
    	cf.setValueDeserializer(new JsonDeserializer<>(CreateOrderResponse.class,false));
    	return cf;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CreateOrderResponse> CreateOrdenerResponseListenerFactory() {
    	ConsumerFactory<String, CreateOrderResponse> cf = orderConsumerFactory();
    	return factory.getListenerFactory(cf);
    }
    
    
    
}
