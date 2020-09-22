package com.julianduru.omarze.controller;


import com.julianduru.util.JSONUtil;
import com.julianduru.Constants;
import com.julianduru.omarze.api.dto.CampaignActionDTO;
import com.julianduru.omarze.api.dto.CampaignDTO;
import com.julianduru.omarze.controller.api.CampaignApprovalController;
import com.julianduru.omarze.controller.api.CampaignController;
import com.julianduru.omarze.data.campaign.CampaignDataProvider;
import com.julianduru.omarze.entities.BackOfficeUser;
import com.julianduru.omarze.entities.PartnerUser;
import com.julianduru.omarze.model.ApprovalAction;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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

public class CampaignControllerTest extends BaseControllerTest {


    CampaignDataProvider campaignDataProvider;


    @Autowired
    public CampaignControllerTest setCampaignDataProvider(CampaignDataProvider campaignDataProvider) {
        this.campaignDataProvider = campaignDataProvider;
        return this;
    }


    @Test
    @WithMockUser(username = BaseControllerTest.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
    public void testBackOfficeAddingCampaign() throws Exception {
        addCampaign();
    }


    @Test
    @WithMockUser(username = BaseControllerTest.TEST_USER, authorities = {PartnerUser.ROLE_ID, BackOfficeUser.ROLE_ID})
    public void testPartnerAddingCampaign() throws Exception {
        addCampaign();
    }


    @Test
    @WithMockUser(username = BaseControllerTest.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
    public void testBackOfficeUserApprovingCampaignAsCreator() throws Exception {
        addCampaignWithUpdate();
    }


    private void addCampaign() throws Exception {
        CampaignDTO campaignDTO = campaignDataProvider.newCampaignDTO();

        mockMvc
            .perform(
                post(CampaignController.PATH)
                    .content(JSONUtil.asJsonString(campaignDTO))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().is2xxSuccessful());
    }



    private void addCampaignWithUpdate() throws Exception {
        CampaignDTO campaignDTO = campaignDataProvider.newCampaignDTO();

        mockMvc
            .perform(
                    post(CampaignController.PATH)
                            .content(JSONUtil.asJsonString(campaignDTO))
                            .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().is2xxSuccessful())
            .andDo(mvcResult -> {
                String json = mvcResult.getResponse().getContentAsString();
                CampaignDTO dtoResult = JSONUtil.fromJsonString(json, CampaignDTO.class);

                approveCampaignByCreator(dtoResult.getId());
            });
    }


    private void approveCampaignByCreator(Long campaignId) throws Exception {
        CampaignActionDTO action = new CampaignActionDTO(campaignId, ApprovalAction.APPROVED);

        mockMvc.perform(
            post(CampaignApprovalController.PATH)
                .content(JSONUtil.asJsonString(action))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is5xxServerError());
    }


    @Test
    @Disabled
    public void testUpdatingCampaign() throws Exception {
        CampaignDTO campaignDTO = campaignDataProvider.newCampaignDTO();

        String updateResponseString = mockMvc.perform(
                post(Constants.API_BASE + "/campaign")
                .content(JSONUtil.asJsonString(campaignDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(
                put(Constants.API_BASE + "/campaign")
                .content(JSONUtil.readObject(updateResponseString, Constants.PAYLOAD_JSON_PATH).toString())
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }


}

