package com.omarze.entities.projection;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.omarze.entities.BackOfficeUser;
import com.omarze.entities.LotteryUser;
import com.omarze.entities.PartnerUser;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;


/**
 * created by julian
 */
@Projection(
    name = "userDetails",
    types = {
        BackOfficeUser.class,
        PartnerUser.class,
        LotteryUser.class
    }
)
public interface UserDetails {


    Long getId();


    String getName();


    String getEmail();


    String getUsername();


    @JsonFormat(pattern = "yyyy-MM-dd hh:mm a")
    LocalDateTime getTimeAdded();


}

