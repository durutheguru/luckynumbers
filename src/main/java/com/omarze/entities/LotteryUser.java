package com.omarze.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

/**
 * created by julian
 */
@Data
@Entity
public class LotteryUser extends ApplicationUser {


    @Column(nullable = false)
    private boolean signedUpWithFacebook = false;


    @Column(length = 30)
    @Size(max = 30, message = "Phone Number cannot exceed 30")
    private String phoneNumber;


}


