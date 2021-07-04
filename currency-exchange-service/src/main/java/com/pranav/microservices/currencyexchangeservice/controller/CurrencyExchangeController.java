package com.pranav.microservices.currencyexchangeservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.pranav.microservices.currencyexchangeservice.model.Display;
import com.pranav.microservices.currencyexchangeservice.model.ExchangeResults;
import com.pranav.microservices.currencyexchangeservice.utility.Constants;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/pranav")
@Slf4j
public class CurrencyExchangeController {

	@Value("${currency.exchange.key}")
	private String key;
	
	@Value("${server.port}")
	private String env;

	@GetMapping(value = "/currency-exchange/from/{source}/to/{target}")
	public ResponseEntity<Display> retriveExchangeValue(@PathVariable String source, @PathVariable String target) {

		log.info("retriveExchangeValue started");
		String uniqueID = UUID.randomUUID().toString();
		ExchangeResults result = getExchangeValues(source, target);
		if(result == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else {
		Display displayResult = new Display();
		displayResult.setId(uniqueID);
		displayResult.setBase_code(result.base_code);
		displayResult.setTarget_code(result.target_code);
		displayResult.setConversion_rate(result.conversion_rate);
		displayResult.setEnvironment(env);
		return new ResponseEntity<Display>(displayResult, HttpStatus.OK);
		}
	}

	private ExchangeResults getExchangeValues(String source, String target) {
		log.info("get exchange value started from an external source");
		// //GET https://v6.exchangerate-api.com/v6/YOUR-API-KEY/pair/EUR/GBP/
		final String uri = Constants.URL + key + "/pair/" + source + "/" + target;
		RestTemplate restTemplate = new RestTemplate();
		ExchangeResults res = null;
		try {
			res = restTemplate.getForObject(uri, ExchangeResults.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		return res;
	}
}
