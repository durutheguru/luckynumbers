package com.omarze.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * created by julian
 */
@ControllerAdvice
public class ControllerExceptionAdvice {


    private final static Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);


    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception e) {

        logger.error(e.getMessage(), e);

        return new ResponseEntity<>(
                new ApiErrorResponse(e), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


}
