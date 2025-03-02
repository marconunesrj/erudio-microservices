package br.com.erudio.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@Configuration
@OpenAPIDefinition(info =
        @Info(title = "Cambio Service API",
                version = "v1",
                description = "Documentation of Cambio Service API"))
public class OpenApiConfiguration {
	
    // https://medium.com/@pubuduc.14/swagger-openapi-specification-3-integration-with-spring-cloud-gateway-part-2-1d670d4ab69a
    
	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
			.components(new Components())
			.info(
				new io.swagger.v3.oas.models.info.Info()
				.title("Cambio Service API")
				.version("v1")
				.license(
					new License()
						.name("Apache 2.0")
						.url("http://springdoc.org")
				)
			);
	}
}