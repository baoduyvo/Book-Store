package org.voduybao.bookstorebackend.configuration;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.voduybao.bookstorebackend.services.user.EducationService;

@TestConfiguration
public class EducationServiceConfig {
    @Bean
    public EducationService educationService() {
        return Mockito.mock(EducationService.class);
    }
}