package com.julianduru.omarze.data.campaign;


import com.julianduru.omarze.data.BaseDataJpaTest;
import com.julianduru.omarze.persistence.CampaignRepository;

import org.junit.jupiter.api.Test;
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
