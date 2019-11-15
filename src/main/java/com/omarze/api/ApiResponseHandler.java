package com.omarze.api;


import com.omarze.api.annotation.DTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.Collection;

/**
 * created by julian
 */
public class ApiResponseHandler {

    private final static String DEFAULT_DONE_MESSAGE = "Request completed";


    public static ApiResponse handleControllerResponse(Object response, DTO dto,  ModelMapper modelMapper) {
        Class<?> targetDto = dto != null ? dto.value() : null;

        if (response instanceof Page) {
            targetDto = extractTargetDTOFromCollection(((Page<?>) response).getContent());
            return handleControllerPageResponse((Page<?>) response, targetDto, modelMapper);
        }
        else if (response instanceof Collection) {
            targetDto = extractTargetDTOFromCollection((Collection<?>) response);
            return handleControllerCollectionResponse((Collection<?>) response, targetDto, modelMapper);
        }

        return handleControllerObjectResponse(response, targetDto,modelMapper);
    }


    private static Class extractTargetDTOFromCollection(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }

        DTO dto = collection.stream().findFirst().get().getClass().getAnnotation(DTO.class);
        if (dto != null) {
            return dto.value();
        }

        return null;
    }


    private static ApiResponse handleControllerObjectResponse(Object response, Class targetDto, ModelMapper modelMapper) {
        if (response instanceof ApiResponse) {
            return (ApiResponse) response;
        }

        return new ApiResponse<>(
                Status.DONE,
                DEFAULT_DONE_MESSAGE,
                targetDto != null ? modelMapper.map(response, targetDto) : response
        );
    }


    private static ApiResponse handleControllerPageResponse(Page<?> response, Class targetDto, ModelMapper modelMapper) {
        return new ApiResponse<>(
                Status.DONE,
                DEFAULT_DONE_MESSAGE,
                response.map(d -> targetDto != null ? modelMapper.map(d, targetDto) : d)
        );
    }


    private static ApiResponse handleControllerCollectionResponse(Collection<?> response, Class targetDto, ModelMapper modelMapper) {
        return new ApiResponse<>(
                Status.DONE,
                DEFAULT_DONE_MESSAGE,
                (response).stream().map(d -> targetDto != null ? modelMapper.map(d, targetDto) : d)
        );
    }


}
