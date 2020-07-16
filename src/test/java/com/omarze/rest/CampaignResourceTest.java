package com.omarze.rest;


import com.omarze.controller.BaseControllerTest;
import com.omarze.entities.BackOfficeUser;
import com.omarze.persistence.CampaignRepository;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql({"/db/scripts/campaign/init.sql"})
@WithMockUser(username = BaseControllerTest.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
public class CampaignResourceTest extends BaseControllerTest {


    @Test
    public void testLoadingCampaigns() throws Exception {
        mockMvc.perform(
            get(API_BASE_PATH + CampaignRepository.PATH + "?projection=campaignDetails")
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

