package com.pranav.microservices.currencyconversionservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pranav.microservices.currencyconversionservice.model.CurrencyConversion;


@FeignClient(name = "exchange-service")
public interface CurrencyExchangeProxy {

	@GetMapping(value = "pranav/currency-exchange/from/{source}/to/{target}")
	public CurrencyConversion retriveExchangeValue(@PathVariable String source, 
			@PathVariable String target);
	
}
