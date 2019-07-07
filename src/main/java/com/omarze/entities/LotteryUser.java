package com.omarze.entities;


import com.omarze.api.annotation.DTO;
import com.omarze.dto.LotteryUserDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

/**
 * created by julian
 */
@Entity
@DTO(LotteryUserDTO.class)
public class LotteryUser extends ApplicationUser {

    @Column(nullable = false)
    private boolean signedUpWithFacebook = false;

    @Column(length = 30)
    @Size(max = 30, message = "Phone Number cannot exceed 30")
    private String phoneNumber;


    public boolean isSignedUpWithFacebook() {
        return signedUpWithFacebook;
    }

    public LotteryUser setSignedUpWithFacebook(boolean signedUpWithFacebook) {
        this.signedUpWithFacebook = signedUpWithFacebook;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LotteryUser setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }


}
