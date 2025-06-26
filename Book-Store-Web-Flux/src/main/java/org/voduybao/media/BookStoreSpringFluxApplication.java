package org.voduybao.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
        "org.voduybao.tools.dao.entities"
})
@EnableJpaRepositories(basePackages = {
        "org.voduybao.tools.dao.repository"
})
public class BookStoreSpringFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreSpringFluxApplication.class, args);
    }

}
