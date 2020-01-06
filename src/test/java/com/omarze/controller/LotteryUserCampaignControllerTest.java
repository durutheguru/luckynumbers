package com.omarze.controller;


import com.omarze.Constants;
import com.omarze.data.lotteryuser.LotteryUserCampaignDataService;
import com.omarze.api.dto.CampaignDTO;
import com.omarze.api.dto.LotteryUserCampaignDTO;
import com.omarze.api.dto.LotteryUserDTO;
import com.omarze.util.JSONUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * created by julian
 */
@WithMockUser
public class LotteryUserCampaignControllerTest extends BaseControllerTest {

    private LotteryUserCampaignDataService userCampaignDataService;


    @Autowired
    public LotteryUserCampaignControllerTest setUserCampaignDataService(LotteryUserCampaignDataService userCampaignDataService) {
        this.userCampaignDataService = userCampaignDataService;
        return this;
    }


    @Test
    public void testAddingNewLotteryUserCampaign() throws Exception {
        LotteryUserCampaignDTO userCampaignDTO = userCampaignDataService.newUserCampaignDTO();

        mockMvc.perform(
                post(Constants.API_BASE + "/user_campaign")
                .content(JSONUtil.asJsonString(userCampaignDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }


}

