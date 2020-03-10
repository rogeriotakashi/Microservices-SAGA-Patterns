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
import com.rogerio.saga.orchestrator.OrchestratorService.models.requests.customer.ReserveCreditRequest;
import com.rogerio.saga.orchestrator.OrchestratorService.models.response.customer.ReserveCreditResponse;


@Configuration
@EnableKafka
public class ReserveCreditResponseConsumerConfig {
	
	@Value("${app.consumers.groupid.reserve-credit-response}")
	private String RESERVE_CREDIT_RESPONSE_GROUP;
	
	@Autowired
	ConsumerConfigFactory<String, ReserveCreditResponse> factory;

	@Bean
    public DefaultKafkaConsumerFactory<String, ReserveCreditResponse> reserveCreditConsumerFactory() {
    	DefaultKafkaConsumerFactory<String, ReserveCreditResponse> cf = factory.createDefaultConsumerFactory();
    	cf.getConfigurationProperties().put(ConsumerConfig.GROUP_ID_CONFIG, RESERVE_CREDIT_RESPONSE_GROUP);
    	cf.setKeyDeserializer(new StringDeserializer());
    	cf.setValueDeserializer(new JsonDeserializer<>(ReserveCreditResponse.class,false));
    	return cf;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ReserveCreditResponse> reseveCreditResponseListenerFactory() {
    	ConsumerFactory<String, ReserveCreditResponse> cf = reserveCreditConsumerFactory();
    	return factory.getListenerFactory(cf);
    }
}
