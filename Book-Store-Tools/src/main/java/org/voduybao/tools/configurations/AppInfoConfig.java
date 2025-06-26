package org.voduybao.tools.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInfoConfig {

    @Bean
    public String applicationName() {
        return "Book-Store-Tools";
    }
}
