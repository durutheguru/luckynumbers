package com.omarze.api.dto;


import lombok.Data;

/**
 * created by julian
 */
@Data
public class LotteryUserDTO extends ApplicationUserDTO {


    private boolean signedUpWithFacebook = false;


    private String phoneNumber;


}





