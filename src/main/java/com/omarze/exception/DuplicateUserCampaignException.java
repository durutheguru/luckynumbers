package com.omarze.exception;


/**
 * created by julian
 */
public class DuplicateUserCampaignException extends ServiceException {


    public final static Integer CODE = 10500413;


    public DuplicateUserCampaignException(Long lotteryUserId, Long campaignId) {
        super(String.format("Duplicate Lottery User %d for campaign %d", lotteryUserId, campaignId));
    }


    @Override
    public Integer generateCode() {
        return CODE;
    }


}
