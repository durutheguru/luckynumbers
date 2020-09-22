package com.julianduru.omarze.service.campaign;


import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.entities.CampaignStatus;
import com.julianduru.omarze.exception.InvalidUpdateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * created by julian
 */
public class AlreadyApprovedCampaignTest extends CampaignActionTest {


    @BeforeEach
    @Override
    public void init() {new Campaign();
        Campaign approvedCampaign = new Campaign();
        approvedCampaign.setCampaignStatus(CampaignStatus.APPROVED);

        campaign = campaignDataProvider.saveEntity(approvedCampaign);
    }


    @Disabled
    @Test
    public void testAlreadyApprovedCampaign() throws Exception {
        assertThrows(InvalidUpdateException.class, super::approveCampaign);
    }


}
