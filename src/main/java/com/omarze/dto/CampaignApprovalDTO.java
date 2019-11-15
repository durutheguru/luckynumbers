package com.omarze.dto;


import com.omarze.model.ApprovalAction;

import javax.validation.constraints.NotNull;

/**
 * created by julian
 */
public class CampaignApprovalDTO {

    @NotNull(message = "Campaign Id is required")
    private Long campaignId;

    @NotNull(message = "Approval Action cannot be empty")
    private ApprovalAction approvalAction;


    public CampaignApprovalDTO(Long campaignId, ApprovalAction approvalAction) {
        this.campaignId = campaignId;
        this.approvalAction = approvalAction;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public CampaignApprovalDTO setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
        return this;
    }

    public ApprovalAction getApprovalAction() {
        return approvalAction;
    }

    public CampaignApprovalDTO setApprovalAction(ApprovalAction approvalAction) {
        this.approvalAction = approvalAction;
        return this;
    }


}
