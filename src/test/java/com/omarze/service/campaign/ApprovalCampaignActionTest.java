package com.omarze.service.campaign;


import com.omarze.entities.CampaignStatus;
import com.omarze.entities.RequestStatus;
import org.junit.Assert;
import org.junit.Test;

/**
 * created by julian
 */
public class ApprovalCampaignActionTest extends CampaignActionTest {


    @Test
    public void testApprovingCampaign() throws Exception {
        approveCampaign();

        Assert.assertEquals(campaign.getRequestStatus(), RequestStatus.APPROVED);
        Assert.assertEquals(campaign.getCampaignStatus(), CampaignStatus.APPROVED);
    }


}
