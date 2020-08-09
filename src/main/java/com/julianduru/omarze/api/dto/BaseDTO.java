package com.julianduru.omarze.api.dto;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * created by julian
 */
@Data
public class BaseDTO {


    public Long id;


    public LocalDateTime timeAdded;


    public LocalDateTime timeUpdated;


}

