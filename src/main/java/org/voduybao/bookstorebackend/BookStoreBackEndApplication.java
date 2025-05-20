package org.voduybao.bookstorebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookStoreBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreBackEndApplication.class, args);
    }

}
