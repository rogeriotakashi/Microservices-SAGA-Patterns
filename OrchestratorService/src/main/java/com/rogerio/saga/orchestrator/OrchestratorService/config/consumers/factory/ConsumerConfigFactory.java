package com.rogerio.saga.orchestrator.OrchestratorService.config.consumers.factory;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import com.rogerio.saga.orchestrator.OrchestratorService.models.response.order.CreateOrderResponse;


@Component
public class ConsumerConfigFactory<K,V> {
	
	/**
	 * Default implementation for ProducerFactory
	 * @return ProducerFactory<E,V>
	 */
	public DefaultKafkaConsumerFactory<K, V> createDefaultConsumerFactory(){
		Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "CreateOrderResponseGroup");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
	}
	
	/**
	 * Default implementation for Listener Factory
	 * @return KafkaTemplate<E,V>
	 */
	public ConcurrentKafkaListenerContainerFactory<K,V> getListenerFactory(ConsumerFactory<K,V> cf) {
        ConcurrentKafkaListenerContainerFactory<K, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(cf);
        return factory;
    }
    
	
}
