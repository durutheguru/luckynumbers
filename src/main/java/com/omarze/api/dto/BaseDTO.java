package com.omarze.api.dto;


import lombok.Data;

import javax.persistence.Id;
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

