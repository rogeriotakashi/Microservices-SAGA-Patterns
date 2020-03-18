package com.rogerio.saga.choreography.DocumentationService.updater;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rogerio.saga.choreography.DocumentationService.context.ServiceDefinitionsContext;

/**
 * 
 * Periodically poll the service instances and update the in memory store as key
 * value pair
 */

@Component
public class ServiceDescriptionUpdater {

	private static final Logger logger = LoggerFactory.getLogger(ServiceDescriptionUpdater.class);

	@Value("${updater.config.default-swagger-url}")
	private String DEFAULT_SWAGGER_URL;

	@Value("${updater.config.key-swagger-url}")
	private String KEY_SWAGGER_URL;

	@Value("${updater.config.service-suffix}")
	private String SERVICE_SUFFIX;
	
	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private ServiceDefinitionsContext definitionContext;

	@Autowired
	private RestTemplate template;

	@Scheduled(fixedDelayString = "${updater.config.refreshrate}")
	public void refreshSwaggerConfigurations() {
		logger.info("Starting Service Definitions Context refresh");
		discoveryClient.getServices().stream()
			.filter((serviceId) -> serviceId.contains(SERVICE_SUFFIX))
			.forEach(serviceId -> {
				String swaggerURL = this.getSwaggerURL(serviceId);
				Optional<Object> jsonData = this.getSwaggerDefinitionForAPI(swaggerURL);
	
				if (jsonData.isPresent()) {
					String content = getJSON(serviceId, jsonData.get());
					definitionContext.addServiceDefinition(serviceId, content);
				} else {
					logger.error("Skipping service id : {} Error: Could not get Swagger definition from API.", serviceId);
				}
	
				logger.info("Service Definition Context Refreshed at : {} ", LocalDate.now());
			});

	}
	
	private String getSwaggerURL(String hostname) {
		return "http://" + hostname + DEFAULT_SWAGGER_URL;
	}

	private Optional<Object> getSwaggerDefinitionForAPI(String url) {
		try {
			Object jsonData = template.getForObject("" + url, Object.class);
			return Optional.of(jsonData);
		} catch (RestClientException ex) {
			logger.error("Error while getting service definition for service : {} Error: {}", url, ex.getMessage());
			return Optional.empty();
		}
	}

	private String getJSON(String serviceId, Object jsonData) {
		try {
			return new ObjectMapper().writeValueAsString(jsonData);
		} catch (JsonProcessingException e) {
			logger.error("Error : {}", e.getMessage());
			return "";
		}
	}

}
