package com.rogerio.saga.orchestrator.OrchestratorService.config.consumers;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.rogerio.saga.orchestrator.OrchestratorService.models.response.customer.ReserveCreditResponse;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.order.CreateOrderResponse;


@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, CreateOrderResponse> orderConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "CreateOrderResponseGroup");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(CreateOrderResponse.class,false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CreateOrderResponse> orderKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CreateOrderResponse> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderConsumerFactory());
        return factory;
    }
    
    
    @Bean
    public ConsumerFactory<String, ReserveCreditResponse> reserveCreditResponseConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "CreateOrderResponseGroup");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(ReserveCreditResponse.class,false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ReserveCreditResponse> reserveCreditKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ReserveCreditResponse> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(reserveCreditResponseConsumerFactory());
        return factory;
    }
}
