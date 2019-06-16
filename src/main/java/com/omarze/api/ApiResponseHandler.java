package com.omarze.api;


import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.Collection;

/**
 * created by julian
 */

public class ApiResponseHandler {

    private final static String DEFAULT_SUCCESS_MESSAGE = "Operation was successful";


    public static ApiResponse handleControllerResponse(Object response, Class targetDto,  ModelMapper modelMapper) {
        if (response instanceof Page) {
            return handleControllerPageResponse(response, targetDto, modelMapper);
        }
        else if (response instanceof Collection) {
            return handleControllerCollectionResponse(response, targetDto, modelMapper);
        }
        else {
            return handleControllerObjectResponse(response, targetDto, modelMapper);
        }
    }


    private static ApiResponse handleControllerPageResponse(Object response, Class targetDto, ModelMapper modelMapper) {
        return new ApiResponse<>(
                Status.SUCCESS,
                DEFAULT_SUCCESS_MESSAGE,
                ((Page<?>) response).map(d -> modelMapper.map(d, targetDto))
        );
    }


    private static ApiResponse handleControllerCollectionResponse(Object response, Class targetDto, ModelMapper modelMapper) {
        return new ApiResponse<>(
                Status.SUCCESS,
                DEFAULT_SUCCESS_MESSAGE,
                ((Collection<?>) response).stream().map(d -> modelMapper.map(d, targetDto))
        );
    }


    private static ApiResponse handleControllerObjectResponse(Object response, Class targetDto, ModelMapper modelMapper) {
        return new ApiResponse<>(
                Status.SUCCESS,
                DEFAULT_SUCCESS_MESSAGE,
                modelMapper.map(response, targetDto)
        );
    }


}
