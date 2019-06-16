package com.omarze.api;



import com.omarze.api.annotation.DTO;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;


/**
 * created by julian
 */
@ControllerAdvice
public class DTOResponseBodyControllerAdvice extends AbstractMappingJacksonResponseBodyAdvice {


    private ModelMapper modelMapper;


    @Autowired
    public DTOResponseBodyControllerAdvice(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return super.supports(returnType, converterType) && returnType.getMethod().getReturnType().getAnnotation(DTO.class) != null;
    }


    @Override
    protected void beforeBodyWriteInternal(
            MappingJacksonValue mappingJacksonValue, MediaType mediaType, MethodParameter methodParameter,
            ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {


        DTO dto = methodParameter.getMethod().getReturnType().getAnnotation(DTO.class);
        if (dto == null) {
            return;
        }

        Object responseValue = mappingJacksonValue.getValue();
        mappingJacksonValue.setValue(ApiResponseHandler.handleControllerResponse(responseValue, dto.value(), modelMapper));
    }


}
