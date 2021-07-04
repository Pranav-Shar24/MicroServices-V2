package com.pranav.microservices.currencyexchangeservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Display {
	
	public String id;
	public String base_code;
	public String target_code;
	public double conversion_rate;
	private String environment;

	public Display(){
		
	}
}
