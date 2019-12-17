package com.omarze.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * created by julian
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionAdvice {


    private final static Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);



    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception e) {
        logger.error("Controller Exception: " + e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorResponse(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(Exception e) {
        logger.error("Controller Exception: " + e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorResponse(e), HttpStatus.FORBIDDEN);
    }


}







