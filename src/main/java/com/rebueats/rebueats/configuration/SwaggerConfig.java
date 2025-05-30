package com.rebueats.rebueats.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI springrebUeatsOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("App de Delivery rebUeats")
						.description("Projeto Integrador Grupo 2 - Generation Brasil")
						.version("v0.0.1")
						.license(new License()
								.name("Generation Brasil")
								.url("https://brazil.generation.org/"))
						.contact(new Contact()
								.name("Grupo 2")
								.url("https://github.com/Generation-Projeto-Integrador-Grupo-2")
								.email("grupo_02-turma-java_81@outlook.com")))
				.externalDocs(new ExternalDocumentation()
						.description("Github")
						.url("https://github.com/Generation-Projeto-Integrador-Grupo-2"));
	}
	
	@Bean
	OpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
		
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations()
					.forEach(operation -> {
						ApiResponses apiResponses = operation.getResponses();
						
						apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
						apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
						apiResponses.addApiResponse("204", createApiResponse("Objeto Excluido!"));
						apiResponses.addApiResponse("200", createApiResponse("Erro na Requisição!"));
						apiResponses.addApiResponse("200", createApiResponse("Acesso Não Autorizado!"));
						apiResponses.addApiResponse("200", createApiResponse("Acesso Proibido!"));
						apiResponses.addApiResponse("200", createApiResponse("Objeto Não Encontrado!"));
						apiResponses.addApiResponse("200", createApiResponse("Erro na Aplicação!"));
						
					}));
		};
	}
	private ApiResponse createApiResponse(String message) {
		
		return new ApiResponse().description(message);
		
	}
}
