package com.omarze.controller;


import com.google.common.base.Strings;
import com.jayway.jsonpath.JsonPath;
import com.omarze.api.dto.PartnerDTO;
import com.omarze.data.partner.PartnerDataService;
import com.omarze.entities.BackOfficeUser;
import com.omarze.util.JSONUtil;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.LinkedHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * created by julian
 */
@WithMockUser(username = PartnerControllerTest.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
public class PartnerControllerTest extends BaseControllerTest {


    public final static String TEST_USER = "Test BackOffice User";


    private PartnerDataService partnerDataService;


    @Autowired
    public PartnerControllerTest setPartnerDataService(PartnerDataService partnerDataService) {
        this.partnerDataService = partnerDataService;
        return this;
    }


    @Test
    public void testSavingPartner() throws Exception {
        PartnerDTO partnerDTO = partnerDataService.newPartnerDTO();
        savePartner(partnerDTO);
    }


    @Test
    public void testSavingAlreadyExistingPartner() throws Exception {
        PartnerDTO partnerDTO = partnerDataService.newPartnerDTO();
        savePartner(partnerDTO);

        mockMvc.perform(
                post("/api/v1/partner")
                .content(JSONUtil.asJsonString(partnerDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is5xxServerError());
    }


    @Test
    public void testUpdatingPartner() throws Exception {

        PartnerDTO partnerDTO = partnerDataService.newPartnerDTO();

        String savedPartnerResponse = mockMvc.perform(
                post("/api/v1/partner")
                .content(JSONUtil.asJsonString(partnerDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(
                put("/api/v1/partner")
                .content(new JSONObject((LinkedHashMap)JsonPath.read(savedPartnerResponse, "$")).toString())
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }


    @Test
    public void testUpdatingPartnerWithNoID() throws Exception {

        PartnerDTO partnerDTO = partnerDataService.newPartnerDTO();

        mockMvc.perform(
                put("/api/v1/partner")
                .content(JSONUtil.asJsonString(partnerDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is5xxServerError());

    }


    @Test
    public void testSavingPartnerWithLongNameCauses5xxError() throws Exception {
        PartnerDTO partnerDTO = partnerDataService.newPartnerDTO();

        partnerDTO.name = Strings.repeat("Long Arse Name", 100);

        mockMvc.perform(
                post("/api/v1/partner")
                .content(JSONUtil.asJsonString(partnerDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is5xxServerError());
    }


    private ResultActions savePartner(PartnerDTO partnerDTO) throws Exception {
        return mockMvc.perform(
                post("/api/v1/partner")
                        .content(JSONUtil.asJsonString(partnerDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }


}

