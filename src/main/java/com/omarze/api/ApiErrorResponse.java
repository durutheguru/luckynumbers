package com.omarze.api;



/**
 * created by julian
 */
public class ApiErrorResponse extends ApiResponse<String> {


    public ApiErrorResponse(String message) {
        super(Status.ERROR, message);
    }


    public ApiErrorResponse(String message, String data) {
        super(Status.ERROR, message, data);
    }


    public ApiErrorResponse(Exception e) {
        super(Status.ERROR, ApiBodySanitizer.sanitizeMessage(e));
    }



}
