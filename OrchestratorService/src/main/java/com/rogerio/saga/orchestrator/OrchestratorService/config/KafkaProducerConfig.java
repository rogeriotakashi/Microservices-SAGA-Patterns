package com.rogerio.saga.orchestrator.OrchestratorService.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.customer.ReserveCreditRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.order.CreateOrderRequest;


@Configuration
public class KafkaProducerConfig {
	
	@Bean
    public ProducerFactory<String, CreateOrderRequest> createOrderProducerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, CreateOrderRequest> createOrderkafkaTemplate() {
        return new KafkaTemplate<>(createOrderProducerFactory());
    }
    
    @Bean
    public ProducerFactory<String, ReserveCreditRequest> reserveCreditProducerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, ReserveCreditRequest> reserveCreditkafkaTemplate() {
        return new KafkaTemplate<>(reserveCreditProducerFactory());
    }
}
