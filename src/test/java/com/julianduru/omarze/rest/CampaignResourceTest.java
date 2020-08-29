package com.julianduru.omarze.rest;


import com.julianduru.omarze.controller.BaseControllerTest;
import com.julianduru.omarze.data.campaign.CampaignDataProvider;
import com.julianduru.omarze.data.campaign.CampaignStageEvaluationResultDataProvider;
import com.julianduru.omarze.entities.BackOfficeUser;
import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.entities.CampaignStageEvaluationResult;
import com.julianduru.omarze.persistence.CampaignRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * created by julian
 */
@Sql(scripts = {"/db/scripts/campaign/init.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db/scripts/campaign/delete.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithMockUser(username = BaseControllerTest.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
public class CampaignResourceTest extends BaseControllerTest {


    @Autowired
    CampaignDataProvider campaignDataProvider;

    @Autowired
    CampaignStageEvaluationResultDataProvider stageEvaluationResultDataProvider;


    @Test
    public void testLoadingCampaigns() throws Exception {
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
        mockMvc.perform(
            get(API_BASE_PATH + CampaignRepository.PATH + "/search/searchCampaign?name=Burna&desc=party")
        ).andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$._embedded.campaigns", not(empty())));
    }


    @Test
    public void testFetchingCampaignsByStatus() throws Exception {
        mockMvc.perform(
            get(API_BASE_PATH + CampaignRepository.PATH + "/search/byStatus?status=APPROVED&projection=campaignDetails")
        ).andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$._embedded.campaigns", not(empty())));
    }


}

