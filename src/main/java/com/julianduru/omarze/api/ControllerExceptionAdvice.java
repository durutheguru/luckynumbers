package com.julianduru.omarze.api;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * created by julian
 */
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler{


    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception e) {
        log.error("Controller Exception: " + e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorResponse(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(Exception e) {
        log.error("Controller Exception: " + e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorResponse(e), HttpStatus.FORBIDDEN);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
        HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        log.error(ex.getMessage(), ex);

        return handleExceptionInternal(ex, null, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
        String error = errorList.get(0).getDefaultMessage();

        return new ResponseEntity<>(new ApiErrorResponse(error), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({RepositoryConstraintViolationException.class})
    public ResponseEntity<List<String>> handleRepositoryConstraintViolationException(RepositoryConstraintViolationException e) {
        Errors violations = e.getErrors();
        List<String> messages = violations.getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());

        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }


}




