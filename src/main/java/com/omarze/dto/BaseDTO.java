package com.omarze.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * created by julian
 */
public class BaseDTO {

    @Id
    public Long id;

    public LocalDateTime timeAdded;

    public LocalDateTime timeUpdated;


    public Long getId() {
        return id;
    }

    public BaseDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getTimeAdded() {
        return timeAdded;
    }

    public BaseDTO setTimeAdded(LocalDateTime timeAdded) {
        this.timeAdded = timeAdded;
        return this;
    }

    public LocalDateTime getTimeUpdated() {
        return timeUpdated;
    }

    public BaseDTO setTimeUpdated(LocalDateTime timeUpdated) {
        this.timeUpdated = timeUpdated;
        return this;
    }


}
