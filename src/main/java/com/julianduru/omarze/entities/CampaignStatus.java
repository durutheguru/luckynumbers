package com.julianduru.omarze.entities;


import com.julianduru.omarze.model.ApprovalAction;

/**
 * created by julian
 */
public enum CampaignStatus {

    AWAITING_APPROVAL,

    APPROVED,

    DISAPPROVED,

    ACTIVE,

    EVALUATING,

    CANCELLED,

    COMPLETED;


    public static CampaignStatus fromApproval(ApprovalAction approvalAction) {
        return approvalAction == ApprovalAction.APPROVED ? APPROVED : DISAPPROVED;
    }


}
