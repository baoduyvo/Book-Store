package org.voduybao.bookstorebackend.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bookstore API")
                        .description("API mô tả các chức năng của hệ thống Bookstore")
                        .version("1.0.0"));
    }
}