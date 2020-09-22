package com.julianduru.omarze.rest;


import com.julianduru.omarze.controller.BaseControllerTest;
import com.julianduru.omarze.data.campaign.CampaignDataProvider;
import com.julianduru.omarze.data.campaign.CampaignStageEvaluationResultDataProvider;
import com.julianduru.omarze.entities.BackOfficeUser;
import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.entities.CampaignStageEvaluationResult;
import com.julianduru.omarze.entities.CampaignStatus;
import com.julianduru.omarze.persistence.CampaignRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * created by julian
 */
@WithMockUser(username = BaseControllerTest.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
public class CampaignResourceTest extends BaseControllerTest {


    @Autowired
    CampaignDataProvider campaignDataProvider;

    @Autowired
    CampaignStageEvaluationResultDataProvider stageEvaluationResultDataProvider;


    @Test
    public void testLoadingCampaigns() throws Exception {
        campaignDataProvider.save(3);

        mockMvc.perform(
            get(API_BASE_PATH + CampaignRepository.PATH + "?projection=campaignDetails")
        ).andDo(print())
            .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void testLoadingSingleCampaignDetails() throws Exception {
        Campaign savedCampaign = campaignDataProvider.save();

        CampaignStageEvaluationResult result = new CampaignStageEvaluationResult();
        result.setCampaign(savedCampaign);
        stageEvaluationResultDataProvider.save(result);

        mockMvc.perform(
            get(API_BASE_PATH + CampaignRepository.PATH + "/" + savedCampaign.getId() + "?projection=campaignDetails")
        ).andDo(print())
            .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void testSearchingCampaigns() throws Exception {
        Campaign sample1 = new Campaign();
        sample1.setName("Burna");

        Campaign sample2 = new Campaign();
        sample2.setDescription("party");

        campaignDataProvider.save(sample1, sample2);

        mockMvc.perform(
            get(API_BASE_PATH + CampaignRepository.PATH + "/search/searchCampaign?name=Burna&desc=party")
        ).andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$._embedded.campaigns", not(empty())));
    }


    @Test
    public void testFetchingCampaignsByStatus() throws Exception {
        Campaign sample = new Campaign();
        sample.setDescription("party");
        sample.setCampaignStatus(CampaignStatus.APPROVED);

        campaignDataProvider.save(sample);

        mockMvc.perform(
            get(API_BASE_PATH + CampaignRepository.PATH + "/search/byStatus?status=APPROVED&projection=campaignDetails")
        ).andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$._embedded.campaigns", not(empty())));
    }


}

