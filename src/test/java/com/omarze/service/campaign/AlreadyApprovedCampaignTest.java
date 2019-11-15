package com.omarze.service.campaign;


import com.omarze.entities.Campaign;
import com.omarze.entities.RequestStatus;
import com.omarze.exception.InvalidUpdateException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * created by julian
 */
public class AlreadyApprovedCampaignTest extends CampaignActionTest {


    @Before
    @Override
    public void init() {
        Campaign approvedCampaign = new Campaign();
        approvedCampaign.setRequestStatus(RequestStatus.APPROVED);

        campaign = campaignDataService.saveCampaign(approvedCampaign);
    }


    @Ignore
    @Test(expected = InvalidUpdateException.class)
    public void testAlreadyApprovedCampaign() throws Exception {
        super.approveCampaign();
    }


}
