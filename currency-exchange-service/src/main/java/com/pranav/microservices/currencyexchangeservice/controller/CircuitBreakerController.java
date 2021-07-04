package com.pranav.microservices.currencyexchangeservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CircuitBreakerController {

	@GetMapping("/sampleAPI")
	@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
	public String getSampleApi() {
		log.info("RETRY: {}");
		ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8000/some-dummy-url", String.class);
		return entity.getBody();
	}
	
	public String hardcodedResponse(Exception ex) {
		log.info("Exception was: {} " ,ex);
		return "Fallback reposne";
	}
}
