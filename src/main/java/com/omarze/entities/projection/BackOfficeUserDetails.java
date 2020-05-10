package com.omarze.entities.projection;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.omarze.entities.BackOfficeUser;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

/**
 * created by julian
 */
@Projection(name = "backOfficeUserDetails", types = {BackOfficeUser.class})
public interface BackOfficeUserDetails {


    Long getId();


    String getName();


    String getEmail();


    String getUsername();


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime getTimeAdded();


}
