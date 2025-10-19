package com.farmermart.notification.Notification.Service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapConfig {

    @Bean
    public ModelMapper createModelMapper() {
        return new ModelMapper();
    }
}
