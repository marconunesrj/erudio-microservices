package br.com.erudio.configuration;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        
		Function<PredicateSpec, Buildable<Route>> routeHello = p -> p.path("/get")
        			.filters(f -> f
        				.addRequestHeader("Hello", "World")
        				.addRequestParameter("Hello", "World"))
        		.uri("http://httpbin.org:80");
		
        Function<PredicateSpec, Buildable<Route>> routeCambioService = p -> p.path("/cambio-service/**")
        		.uri("lb://cambio-service");  // lb -> loadBalancer do Eureka
        
        Function<PredicateSpec, Buildable<Route>> routeBookService = p -> p.path("/book-service/**")
        		.uri("lb://book-service");  // lb -> loadBalancer do Eureka
        
        return builder.routes()
				.route(routeHello)
				.route(routeCambioService)
				.route(routeBookService)
				.build();
	}

}