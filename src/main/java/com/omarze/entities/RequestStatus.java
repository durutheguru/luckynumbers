package com.omarze.entities;


import com.omarze.model.ApprovalAction;

/**
 * created by julian
 */
public enum RequestStatus {

    PENDING,

    APPROVED,

    DISAPPROVED;


    public static RequestStatus fromApproval(ApprovalAction action) {
        if (action == ApprovalAction.APPROVED) {
            return APPROVED;
        }
        else {
            return DISAPPROVED;
        }
    }


}
