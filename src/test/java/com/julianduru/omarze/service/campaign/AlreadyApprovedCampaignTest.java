package com.julianduru.omarze.service.campaign;


import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.entities.CampaignStatus;
import com.julianduru.omarze.exception.InvalidUpdateException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * created by julian
 */
public class AlreadyApprovedCampaignTest extends CampaignActionTest {


    @Before
    @Override
    public void init() {new Campaign();
        Campaign approvedCampaign = new Campaign();
        approvedCampaign.setCampaignStatus(CampaignStatus.APPROVED);

        campaign = campaignDataProvider.saveEntity(approvedCampaign);
    }


    @Ignore
    @Test(expected = InvalidUpdateException.class)
    public void testAlreadyApprovedCampaign() throws Exception {
        super.approveCampaign();
    }


}
