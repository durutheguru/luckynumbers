package com.omarze.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarze.util.DTOModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * created by julian
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final ModelMapper modelMapper;
    private final EntityManager entityManager;
    private final ApplicationContext applicationContext;


    @Autowired
    public WebMvcConfig(EntityManager entityManager, ModelMapper modelMapper, ApplicationContext applicationContext) {
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
        this.applicationContext = applicationContext;
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .applicationContext(applicationContext)
                .build();

        resolvers.add(new DTOModelMapper(objectMapper, modelMapper, entityManager));
    }


}
