package com.pranav.microservices.currencyexchangeservice.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ExchangeResults {
	public String result;
	public String documentation;
	public String terms_of_use;
	public int time_last_update_unix;
	public String time_last_update_utc;
	public int time_next_update_unix;
	public String time_next_update_utc;
	public String base_code;
	public String target_code;
	public double conversion_rate;
	public double conversion_result;
	
	public ExchangeResults() {
		// TODO Auto-generated constructor stub
	}
}
