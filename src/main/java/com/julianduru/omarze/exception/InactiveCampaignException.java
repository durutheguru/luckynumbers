package com.julianduru.omarze.exception;


/**
 * created by julian
 */
public class InactiveCampaignException extends ServiceException{


    public InactiveCampaignException(Long campaignId)  {
        super(String.format("Campaign %d is currently inactive", campaignId));
    }


    public InactiveCampaignException(String message, Throwable cause) {
        super(message, cause);
    }



}
