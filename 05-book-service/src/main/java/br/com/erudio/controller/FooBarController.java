package br.com.erudio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Foo bar")
@RestController
@RequestMapping("book-service")
public class FooBarController {
	
	private Logger logger = LoggerFactory.getLogger(FooBarController.class); 

	// https://resilience4j.readme.io/docs/getting-started
	
	@GetMapping("/foo-bar")
    @Operation(summary = "Foo bar")
	//@Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
	//@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
	// Limita o número de requisições em um determinado período de tempo para este Endpoint, 
	// depende da quantidade de instâncias (loadBalancer) estão rodando
	//@RateLimiter(name = "default") 
	@Bulkhead(name = "default")  // Quantas requisições concorrentes podem ser executadas
	public String fooBar() {
		logger.info("Request to foo-bar is received!");
		/*
		 * // Simulando um erro
		 * var response = new RestTemplate()
		 *  // Este endereço não existe
		 * .getForEntity("http://localhost:8080/foo-bar", String.class);
		 */
		return "Foo-Bar!!!";
		//return response.getBody();
	}
	
	// Pode configurar com direfentes tipos de Exception
	public String fallbackMethod(Exception ex) {
		return "fallbackMethod foo-bar!!!";
	}
}
