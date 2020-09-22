package com.julianduru.omarze.service.campaign;


import com.julianduru.omarze.entities.CampaignStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian
 */
public class ApprovalCampaignActionTest extends CampaignActionTest {


    @Test
    @Disabled
    public void testApprovingCampaign() throws Exception {
        approveCampaign();

        assertThat(campaign.getCampaignStatus()).isEqualTo(CampaignStatus.APPROVED);
    }


}
