package com.omarze.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * created by julian
 */
@ControllerAdvice
public class MainControllerAdvice {


    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception e) {
        return new ResponseEntity<>(
                new ApiErrorResponse(e), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


}
