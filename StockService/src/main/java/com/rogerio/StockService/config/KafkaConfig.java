package com.rogerio.StockService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import lombok.Getter;

@Configuration
@Getter
public class KafkaConfig {
	
	@Value("${app.topic.process-order-request}")
	protected String processOrderRequestTopic;
	
	@Value("${app.topic.response-validator}")
	protected String responseValidatorTopic;	

	@Bean
	public StringJsonMessageConverter converter() {
		return new StringJsonMessageConverter();
	}
}
