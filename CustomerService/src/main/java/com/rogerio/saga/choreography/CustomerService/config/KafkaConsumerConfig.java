package com.rogerio.saga.choreography.CustomerService.config;

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

import com.rogerio.saga.choreography.CustomerService.models.requests.ReserveCreditRequest;


@Configuration
@EnableKafka
public class KafkaConsumerConfig {


    @Bean
    public ConsumerFactory<String, ReserveCreditRequest> reserveCreditConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "ReserveCreditRequestGroup");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(ReserveCreditRequest.class,false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ReserveCreditRequest> reserveCreditKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ReserveCreditRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(reserveCreditConsumerFactory());
        return factory;
    }
}
