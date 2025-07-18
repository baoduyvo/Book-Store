package org.voduybao.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EntityScan(basePackages = {
        "org.voduybao.tools.entities"
})
@EnableWebFlux
public class BookStoreSpringFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreSpringFluxApplication.class, args);
    }

}
