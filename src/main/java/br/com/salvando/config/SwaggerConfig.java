package br.com.salvando.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Salvando API")
                        .version("1.0.0")
                        .description("API RESTful para gerenciamento de usuários, sensores e eventos")
                        .contact(new Contact()
                                .name("Equipe Salvando")
                                .email("contato@salvando.com.br")
                        )
                );
    }
}