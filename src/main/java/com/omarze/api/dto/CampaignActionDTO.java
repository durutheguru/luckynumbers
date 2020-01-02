package com.omarze.api.dto;


import com.omarze.model.ApprovalAction;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * created by julian
 */
@Data
@NoArgsConstructor
public class CampaignActionDTO {


    @NotNull(message = "Campaign Id is required")
    private Long campaignId;


    @NotNull(message = "Approval Action cannot be empty")
    private ApprovalAction approvalAction;


    public CampaignActionDTO(Long campaignId, ApprovalAction approvalAction) {
        this.campaignId = campaignId;
        this.approvalAction = approvalAction;
    }


}


