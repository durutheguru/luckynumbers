package com.omarze.exception;


/**
 * created by julian
 */
public class InactiveCampaignException extends ServiceException{

    public final static Integer CODE = 1069892;

    public InactiveCampaignException(Long campaignId)  {
        super(String.format("Campaign %d is currently inactive", campaignId));
    }


    public InactiveCampaignException(String message, Throwable cause) {
        super(message, cause);
    }


    @Override
    public Integer generateCode() {
        return CODE;
    }


}
