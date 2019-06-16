package com.omarze.api;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * created by julian
 */
public class ApiErrorResponse extends ApiResponse<String> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    public LocalDateTime timeStamp;


    public ApiErrorResponse() {
        super(Status.ERROR, null);
        initTimeStamp();
    }

    public ApiErrorResponse(String message) {
        super(Status.ERROR, message);
        initTimeStamp();
    }


    public ApiErrorResponse(String message, String data) {
        super(Status.ERROR, message, data);
        initTimeStamp();
    }


    public ApiErrorResponse(Exception e) {
        super(Status.ERROR, ApiBodySanitizer.sanitizeMessage(e));
        initTimeStamp();
    }


    private void initTimeStamp() {
        timeStamp = LocalDateTime.now();
    }


}
