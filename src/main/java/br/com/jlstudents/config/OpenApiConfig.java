package br.com.jlstudents.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("JL Students CRUD API")
						.description("Cadastra um estudante e suas respectivas notas semestrais. Deve-se preencher todos os campos. ")
						.contact(new Contact().name("Leila Fernanda da Silva").email("leilafernandadasilva@gmail.com"))
						.version("1.0.0"));
	}
	
}
