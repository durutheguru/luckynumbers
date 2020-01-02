package com.omarze.controller;


import com.omarze.Constants;
import com.omarze.api.dto.CampaignActionDTO;
import com.omarze.data.campaign.CampaignDataService;
import com.omarze.api.dto.CampaignDTO;
import com.omarze.entities.BackOfficeUser;
import com.omarze.entities.PartnerUser;
import com.omarze.model.ApprovalAction;
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

public class CampaignControllerTest extends BaseControllerTest {


    CampaignDataService campaignDataService;


    @Autowired
    public CampaignControllerTest setCampaignDataService(CampaignDataService campaignDataService) {
        this.campaignDataService = campaignDataService;
        return this;
    }


    @Test
    @WithMockUser(username = BaseControllerTest.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
    public void testBackOfficeAddingCampaign() throws Exception {
        addCampaign();
    }


    @Test
    @WithMockUser(username = BaseControllerTest.TEST_USER, authorities = {PartnerUser.ROLE_ID})
    public void testPartnerAddingCampaign() throws Exception {
        addCampaign();
    }


    @Test
    @WithMockUser(username = BaseControllerTest.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
    public void testBackOfficeUserApprovingCampaignAsCreator() throws Exception {
        addCampaignWithUpdate();
    }


    private void addCampaign() throws Exception {
        CampaignDTO campaignDTO = campaignDataService.newCampaignDTO();

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
        CampaignDTO campaignDTO = campaignDataService.newCampaignDTO();

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
    @Ignore
    public void testUpdatingCampaign() throws Exception {
        CampaignDTO campaignDTO = campaignDataService.newCampaignDTO();

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

