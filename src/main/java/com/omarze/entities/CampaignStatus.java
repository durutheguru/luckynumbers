package com.omarze.entities;


import com.omarze.model.ApprovalAction;

/**
 * created by julian
 */
public enum CampaignStatus {

    AWAITING_APPROVAL,

    APPROVED,

    DISAPPROVED,

    ACTIVE,

    CANCELLED,

    COMPLETED;


    public static CampaignStatus fromApproval(ApprovalAction approvalAction) {
        return approvalAction == ApprovalAction.APPROVED ? APPROVED : DISAPPROVED;
    }


}
