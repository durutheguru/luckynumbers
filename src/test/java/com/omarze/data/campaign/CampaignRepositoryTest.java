package com.omarze.data.campaign;


import com.omarze.data.BaseDataJpaTest;
import com.omarze.persistence.CampaignRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * created by julian
 */
public class CampaignRepositoryTest extends BaseDataJpaTest {

    @Autowired
    private CampaignRepository campaignRepository;


    @Test
    public void testLoadingCampaignsDueForActivation() throws Exception {

    }



}
