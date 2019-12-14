package com.omarze.controller;


import com.omarze.Constants;
import com.omarze.data.campaign.CampaignDataService;
import com.omarze.api.dto.CampaignDTO;
import com.omarze.util.JSONUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * created by julian
 */
@WithMockUser
public class CampaignControllerTest extends BaseControllerTest {

    CampaignDataService campaignDataService;


    @Autowired
    public CampaignControllerTest setCampaignDataService(CampaignDataService campaignDataService) {
        this.campaignDataService = campaignDataService;
        return this;
    }


    @Test
    public void testAddingCampaign() throws Exception {
        CampaignDTO campaignDTO = campaignDataService.newCampaignDTO();

        mockMvc.perform(
                post(Constants.API_BASE + "/campaigns")
                .content(JSONUtil.asJsonString(campaignDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());

    }


    @Test
    @Ignore
    public void testUpdatingCampaign() throws Exception {
        CampaignDTO campaignDTO = campaignDataService.newCampaignDTO();

        String updateResponseString = mockMvc.perform(
                post(Constants.API_BASE + "/campaigns")
                .content(JSONUtil.asJsonString(campaignDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(
                put(Constants.API_BASE + "/campaigns")
                .content(JSONUtil.readObject(updateResponseString, Constants.PAYLOAD_JSON_PATH).toString())
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }



}
