package com.omarze.service.campaign;


import com.omarze.api.dto.CampaignApprovalDTO;
import com.omarze.entities.CampaignStatus;
import com.omarze.model.ApprovalAction;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * created by julian
 */
public class DisapproveCampaignActionTest extends CampaignActionTest {


    @Test
    @Ignore
    public void testDisapprovingCampaign() throws Exception {
        CampaignApprovalDTO approvalDTO = new CampaignApprovalDTO(campaign.getId(), ApprovalAction.DISAPPROVED);
        campaign = campaignService.campaignAction(approvalDTO);

        Assert.assertEquals(campaign.getCampaignStatus(), CampaignStatus.DISAPPROVED);
    }


}
