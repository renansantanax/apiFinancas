package br.com.cotiinformatica.infrastructure.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfiguration {
	@Bean
	OpenAPI customOpenApi() {
		return new OpenAPI().components(new Components())
				.info(new Info().title("Finanças API - COTI Informática")
						.description("API Spring Boot para controle de contas a pagar e receber.").version("v1")
						.termsOfService("https://www.cotiinformatica.com.br/termos-de-servico")
						.contact(new Contact().name("Suporte COTI Informática").email("suporte@cotiinformatica.com.br")
								.url("https://www.cotiinformatica.com.br"))
						.license(new License().name("MIT License").url("https://opensource.org/licenses/MIT")));
	}
}
