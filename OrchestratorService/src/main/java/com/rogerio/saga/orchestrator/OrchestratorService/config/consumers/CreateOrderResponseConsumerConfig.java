package com.rogerio.saga.orchestrator.OrchestratorService.config.consumers;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.rogerio.saga.orchestrator.OrchestratorService.models.response.order.CreateOrderResponse;


@Configuration
@EnableKafka
public class CreateOrderResponseConsumerConfig {
	
	@Value("${app.consumers.groupid.create-order-response}")
	private String createOrderResponseGroupId;
	
	@Value("${kafka.server}")
	private String kafkaServer;


	
	@Bean
    public ConsumerFactory<String, CreateOrderResponse> createOrderConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, createOrderResponseGroupId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(CreateOrderResponse.class,false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CreateOrderResponse> createOrderResponseListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CreateOrderResponse> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(createOrderConsumerFactory());
        return factory;
    }
    
    
    
}
