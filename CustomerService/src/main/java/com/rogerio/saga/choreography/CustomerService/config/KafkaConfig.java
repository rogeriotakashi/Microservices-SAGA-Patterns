package com.rogerio.saga.choreography.CustomerService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaConfig {

	
	@Bean
	public StringJsonMessageConverter converter () {
		return new StringJsonMessageConverter();
	}
}
