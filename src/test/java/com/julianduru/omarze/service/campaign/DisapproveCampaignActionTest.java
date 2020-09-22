package com.julianduru.omarze.service.campaign;


import com.julianduru.omarze.api.dto.CampaignActionDTO;
import com.julianduru.omarze.entities.CampaignStatus;
import com.julianduru.omarze.model.ApprovalAction;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian
 */
public class DisapproveCampaignActionTest extends CampaignActionTest {


    @Test
    @Disabled
    public void testDisapprovingCampaign() throws Exception {
        CampaignActionDTO approvalDTO = new CampaignActionDTO(campaign.getId(), ApprovalAction.DISAPPROVED);
        campaign = campaignService.campaignAction(approvalDTO);

        assertThat(campaign.getCampaignStatus()).isEqualTo(CampaignStatus.DISAPPROVED);
    }


}

