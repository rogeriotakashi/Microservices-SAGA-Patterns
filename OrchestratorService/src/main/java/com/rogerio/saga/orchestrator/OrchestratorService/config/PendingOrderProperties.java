package com.rogerio.saga.orchestrator.OrchestratorService.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix="pending-order-properties")
@Data
public class PendingOrderProperties {

	private List<String> serviceVerificationList; 
}
