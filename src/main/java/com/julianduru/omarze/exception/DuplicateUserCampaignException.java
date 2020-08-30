package com.julianduru.omarze.exception;


/**
 * created by julian
 */
public class DuplicateUserCampaignException extends ServiceException {


    public DuplicateUserCampaignException(Long lotteryUserId, Long campaignId) {
        super("Already registered for Campaign");
    }



}
