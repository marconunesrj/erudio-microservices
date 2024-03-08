package br.com.erudio.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

// https://github.com/springdoc/springdoc-openapi/issues/717
// https://github.com/springdoc/springdoc-openapi-demos/tree/master
// https://piotrminkowski.com/2020/02/20/microservices-api-documentation-with-springdoc-openapi/

@Configuration
//@Profile({"local", "dev"})
@OpenAPIDefinition(info = @Info(title = "API Gateway", version = "1.0", description = "Documentation API Gateway v1.0"))
public class OpenApiConfiguration {
	
//    @Bean
//    @Lazy(false)
//    List<GroupedOpenApi> apis(
//            SwaggerUiConfigParameters config,
//            RouteDefinitionLocator locator) {
//        
//        List<GroupedOpenApi> groups = new ArrayList<>();
//        
//        var definitions = locator.getRouteDefinitions().collectList().block();
//        
//        definitions.stream().filter(
//                    routeDefinition -> routeDefinition.getId()
//                        .matches(".*-service"))
//                            .forEach(routeDefinition -> {
//                                String name = routeDefinition.getId();
//                                config.addGroup(name);
//                                groups.add(GroupedOpenApi.builder()
//                                        .pathsToMatch("/" + name + "/**")
//                                        .group(name)
//                                        .build()
//                                    );
//                            }
//                );
//        return groups;
//    }
    
    @Bean
    @Lazy(false)
    List<GroupedOpenApi> apis(
            SwaggerUiConfigParameters config,
            RouteDefinitionLocator locator) {
        
        var definitions = locator.getRouteDefinitions().collectList().block();
        
        definitions.stream().filter(
                    routeDefinition -> routeDefinition.getId()
                        .matches(".*-service"))
                            .forEach(routeDefinition -> {
                                String name = routeDefinition.getId();
                                config.addGroup(name);
                                GroupedOpenApi.builder()
                                    .pathsToMatch("/" + name + "/**")
                                    .group(name).build();
                            }
                );
        return new ArrayList<>();
    }
    
//    https://github.com/springdoc/springdoc-openapi/issues/717
//    @Bean
//    List<GroupedOpenApi> apis2(SwaggerUiConfigProperties config,
//            RouteDefinitionLocator locator) {
//        
//        List<GroupedOpenApi> groups = new ArrayList<>();
//        
//        List<RouteDefinition> definitions = locator
//                .getRouteDefinitions()
//                .collectList()
//                .block();
//        
//        definitions.stream()
//                .filter(routeDefinition ->
//                        routeDefinition.getId()
//                          .matches(".*-service"))
//                .forEach(routeDefinition -> {
//                    config.addGroup(routeDefinition.getId());
//                    groups.add(GroupedOpenApi.builder()
//                        .pathsToMatch("/" + routeDefinition.getId() + "/**")
//                        .group(routeDefinition.getId())
//                        .build()
//                    );
//                });
//        return groups;
//    }

}