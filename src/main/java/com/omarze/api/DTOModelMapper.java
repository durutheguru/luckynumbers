package com.omarze.api;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.omarze.api.annotation.DTO;
import org.modelmapper.ModelMapper;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collections;

/**
 * created by julian
 */
public class DTOModelMapper extends RequestResponseBodyMethodProcessor {

    private final ModelMapper modelMapper;

    private final EntityManager entityManager;


    public DTOModelMapper(ObjectMapper objectMapper, ModelMapper modelMapper, EntityManager entityManager) {
        super(Collections.singletonList(new MappingJackson2HttpMessageConverter(objectMapper)));

        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
    }


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().getAnnotation(DTO.class) != null;
    }


    @Override
    protected void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
        binder.validate();
    }


    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object dto = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
        Object id = getEntityId(dto);

        if (id == null) {
            return modelMapper.map(dto, parameter.getParameterType());
        }
        else {
            Object persistedObject = entityManager.find(parameter.getParameterType(), id);
            modelMapper.map(dto, persistedObject);
            return persistedObject;
        }
    }


    @Override
    protected Object readWithMessageConverters(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType)
            throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {

        DTO dtoType = parameter.getParameterType().getAnnotation(DTO.class);
        if (dtoType != null) {
            return super.readWithMessageConverters(inputMessage, parameter, dtoType.value());
        }

        throw new RuntimeException();
    }


    private Object getEntityId(@NotNull Object dto) {
        for (Field field : dto.getClass().getFields()) {
            if (field.getAnnotation(Id.class) != null) {
                try {
                    field.setAccessible(true);
                    return field.get(dto);
                }
                catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return null;
    }


}
