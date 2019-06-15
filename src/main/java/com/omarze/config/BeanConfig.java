package com.omarze.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by julian
 */
@Configuration
public class BeanConfig {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
