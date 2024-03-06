package br.com.erudio.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope  // Utilizado para atualizar automaticamente as configurações caso haja alguma alteração 
               // enquanto estiver rodando
@ConfigurationProperties("greeting-service")
public class GreetingConfiguration {

	private String greeting;
	private String defaultValue;
	
	public GreetingConfiguration() {}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}