package com.rogerio.saga.choreography.DocumentationService.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rogerio.saga.choreography.DocumentationService.context.ServiceDefinitionsContext;


/**
 * 
 *  Controller to serve the JSON from our in-memory store. So that UI can render the API-Documentation
 */
@RestController
public class ServiceDefinitionResource {

	@Autowired
	private ServiceDefinitionsContext definitionContext;
	
	@GetMapping("/service/{servicename}")
	public String getServiceDefinition(@PathVariable("servicename") String serviceName) {
		return definitionContext.getSwaggerDefinition(serviceName);
	}
}
