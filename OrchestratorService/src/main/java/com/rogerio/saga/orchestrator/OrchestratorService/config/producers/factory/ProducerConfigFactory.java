package com.rogerio.saga.orchestrator.OrchestratorService.config.producers.factory;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;


@Component
public class ProducerConfigFactory<K,V> {
	
	@Value("${kafka.server}")
	private String KAFKA_SERVER;
	
	/**
	 * Default implementation for ProducerFactory
	 * @return ProducerFactory<E,V>
	 */
	public ProducerFactory<K, V> createDefaultProducerFactory(){
		Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
	}
	
	/**
	 * Default implementation for KafkaTemplate
	 * @return KafkaTemplate<E,V>
	 */
	public KafkaTemplate<K, V> createDefaultkafkaTemplate() {
        return new KafkaTemplate<>(createDefaultProducerFactory());
    }
	
	
}
