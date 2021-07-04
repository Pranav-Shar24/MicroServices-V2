package com.pranav.microservices.apigateway.configuration;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get").filters(f -> f.addRequestHeader("MyHeader", "MyHeader")
						.addRequestParameter("MyParma", "MyParam"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/pranav/currency-exchange/**")
						.uri("lb://exchange-service"))
				.route(p -> p.path("/pranav/currency-converter/**")
						.uri("lb://conversion-service"))
				.route(p-> p.path("/pranav/currency-converter-feing/**")
						.uri("lb://conversion-service"))
				.route(p -> p.path("/pranav/currency-converter-new/**")
						.filters(f -> f.rewritePath("/pranav/currency-converter-new/(?<segment>.*)", "/pranav/currency-converter-feing/${segment}"))
						.uri("lb://conversion-service"))
				.build();
	}
}
