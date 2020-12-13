package com.julianduru.omarze.entities;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * created by julian
 */
@Getter
@RequiredArgsConstructor
public enum EmailType {


    TEXT("text/plain"),

    HTML("text/html");


    private final String mime;


}


