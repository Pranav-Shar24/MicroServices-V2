package com.pranav.microservices.currencyconversionservice.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CurrencyConversion {
	public String id;
	public String base_code;
	public String target_code;
	public BigDecimal conversion_rate;
	private BigDecimal qunatity;
	private BigDecimal totalCalculatedAmount;
	private String environment;
	
	public CurrencyConversion() {
	}
}
