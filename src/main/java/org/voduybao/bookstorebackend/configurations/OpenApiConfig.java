package org.voduybao.bookstorebackend.configurations;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI bookstoreOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bookstore Backend API")
                        .description("API documentation for Bookstore Backend")
                        .version("v1.0.0")
                        .contact(new Contact().name("Voduybao").email("contact@example.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Project Wiki Documentation")
                        .url("https://github.com/your-repo"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("bookstore-public")
                .packagesToScan("org.voduybao.bookstorebackend.controllers.v1")
                .pathsToMatch("/v1/**")
                .build();
    }
}
