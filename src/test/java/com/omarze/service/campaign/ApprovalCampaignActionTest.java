package com.omarze.service.campaign;


import com.omarze.entities.CampaignStatus;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * created by julian
 */
public class ApprovalCampaignActionTest extends CampaignActionTest {


    @Test
    @Ignore
    public void testApprovingCampaign() throws Exception {
        approveCampaign();

        Assert.assertEquals(campaign.getCampaignStatus(), CampaignStatus.APPROVED);
    }


}
