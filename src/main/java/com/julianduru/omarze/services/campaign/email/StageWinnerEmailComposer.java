package com.julianduru.omarze.services.campaign.email;


import com.julianduru.omarze.entities.Email;

/**
 * created by julian
 */
public interface StageWinnerEmailComposer {


    Email composeEmail(StageWinnerEmailRequest request);


}
