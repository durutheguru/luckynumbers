package com.julianduru.omarze.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

/**
 * created by julian
 */
@Data
@Entity
public class LotteryUser extends ApplicationUser {


    public static final String ROLE_ID = "LOTTERY_USER";


    @Column(nullable = false)
    private boolean signedUpWithFacebook = false;


    @Column(length = 30)
    @Size(max = 30, message = "Phone Number cannot exceed 30")
    private String phoneNumber;


    @Override
    protected List<String> roles() {
        return Collections.singletonList(ROLE_ID);
    }


}


