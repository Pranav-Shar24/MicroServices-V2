package com.pranav.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pranav.microservices.currencyconversionservice.model.CurrencyConversion;
import com.pranav.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/pranav")
@Slf4j
public class CurrencyConversionController {

	@Value("${server.port}")
	private String env;

	@Autowired(required = true)
	private CurrencyExchangeProxy proxy;
	
	@GetMapping(value = "/currency-converter/from/{source}/to/{target}/quantity/")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String source, @PathVariable String target,
			@RequestParam BigDecimal quantity) {
		log.info("calculateCurrencyConversion started");
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("source", source);
		uriVariables.put("target", target);
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/pranav/currency-exchange/from/{source}/to/{target}/", CurrencyConversion.class,
				uriVariables);

		CurrencyConversion currencyData = responseEntity.getBody();

		return new CurrencyConversion(currencyData.getId(), currencyData.getBase_code(), currencyData.getTarget_code(),
				currencyData.getConversion_rate(), quantity, quantity.multiply(currencyData.getConversion_rate()),
				currencyData.getEnvironment() + " rest template");

	}
	
	@GetMapping(value = "/currency-converter-feing/from/{source}/to/{target}/quantity/")
	public CurrencyConversion calculateCurrencyConversionFeing(@PathVariable String source,
			@PathVariable String target,
			@RequestParam BigDecimal quantity) {
		log.info("calculateCurrencyConversion started feing");
		
		
		CurrencyConversion currencyData = proxy.retriveExchangeValue(source, target);
	
		return new CurrencyConversion(currencyData.getId(), 
				currencyData.getBase_code(), 
				currencyData.getTarget_code(), 
				currencyData.getConversion_rate(), 
				quantity,
				quantity.multiply(currencyData.getConversion_rate()), 
				currencyData.getEnvironment() + " feing");
		
		
	}
}
