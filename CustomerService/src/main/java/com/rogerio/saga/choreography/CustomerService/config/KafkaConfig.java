package com.rogerio.saga.choreography.CustomerService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import lombok.Getter;

@Configuration
@Getter
public class KafkaConfig {
	
	@Value("${app.topic.reserve-credit-request}")
	private String reserveCreditRequestTopic;
	
	@Value("${app.topic.response-validator}")
	private String responseValidatorTopic;

	
	/* This bean is necessary to convert the  Message (Json) to String for StringDeserialization on @KafkaListener (Consumer)*/
	@Bean
	public StringJsonMessageConverter converter () {
		return new StringJsonMessageConverter();
	}
}
