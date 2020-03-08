package com.rogerio.saga.choreography.DocumentationService.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.rogerio.saga.choreography.DocumentationService.context.ServiceDefinitionsContext;

import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 
 *  Swagger Ui configurations. Configure bean of the SwaggerResourcesProvider to read data from in-memory context
 */

@Configuration
@EnableSwagger2
@EnableScheduling
@EnableDiscoveryClient
public class SwaggerUIConfiguration {

	@Autowired
	private ServiceDefinitionsContext definitionContext;
	
	@Primary
	@Bean
	public SwaggerResourcesProvider swaggerResourcesProvider(InMemorySwaggerResourcesProvider defaultResourcesProvider) {
		return () -> {
			List<SwaggerResource> resources = new ArrayList<>(defaultResourcesProvider.get());
			resources.clear();
			resources.addAll(definitionContext.getSwaggerDefinitions());
			return resources;
		};
	}
	
}
