package com.omarze.api;


import com.omarze.util.AppLogger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * created by julian
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionAdvice {


    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception e) {
        AppLogger.error("Controller Exception: " + e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorResponse(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(Exception e) {
        AppLogger.error("Controller Exception: " + e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorResponse(e), HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException(Exception e) {
        AppLogger.error("Controller Exception: " + e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorResponse("Request Message is not readable"), HttpStatus.BAD_REQUEST);
    }


}







