package org.voduybao.media.configurations;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("My WebFlux API")
                        .version("1.0.0")
                        .description("API docs for Spring WebFlux project")
                        .contact(new Contact().name("Your Name").email("your@email.com"))
                );
    }
}