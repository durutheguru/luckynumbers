package com.omarze.controller;


import com.omarze.Constants;
import com.omarze.data.LotteryUserCampaignDataService;
import com.omarze.dto.CampaignDTO;
import com.omarze.dto.LotteryUserCampaignDTO;
import com.omarze.dto.LotteryUserDTO;
import com.omarze.util.JSONUtil;
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

        CampaignDTO campaignDTO = new CampaignDTO();
        LotteryUserDTO lotteryUserDTO = new LotteryUserDTO();

        campaignDTO.setId(userCampaignDTO.getCampaign().getId());
        lotteryUserDTO.setId(userCampaignDTO.getLotteryUser().getId());

        userCampaignDTO.setCampaign(campaignDTO);
        userCampaignDTO.setLotteryUser(lotteryUserDTO);

        mockMvc.perform(
                post(Constants.API_V1_BASE + "/user_campaigns")
                .content(JSONUtil.asJsonString(userCampaignDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }


}
