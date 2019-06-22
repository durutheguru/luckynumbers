package com.omarze.controller;


import com.google.common.base.Strings;
import com.jayway.jsonpath.JsonPath;
import com.omarze.dto.PartnerDTO;
import com.omarze.exception.InvalidEntityException;
import com.omarze.exception.PartnerAlreadyExistsException;
import com.omarze.util.JSONUtil;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.LinkedHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * created by julian
 */
public class PartnerControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testSavingPartner() throws Exception {
        PartnerDTO partnerDTO = newPartner();
        savePartner(partnerDTO);
    }


    @Test
    public void testSavingAlreadyExistingPartner() throws Exception {

        PartnerDTO partnerDTO = newPartner();
        savePartner(partnerDTO);

        mockMvc.perform(
                post("/api/v1/partners")
                .content(JSONUtil.asJsonString(partnerDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.code").value(PartnerAlreadyExistsException.CODE));
    }


    @Test
    public void testUpdatingPartner() throws Exception {

        PartnerDTO partnerDTO = newPartner();

        String savedPartnerResponse = mockMvc.perform(
                post("/api/v1/partners")
                .content(JSONUtil.asJsonString(partnerDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();


        mockMvc.perform(
                put("/api/v1/partners")
                .content(new JSONObject((LinkedHashMap)JsonPath.read(savedPartnerResponse, "$.data")).toString())
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }


    @Test
    public void testUpdatingPartnerWithNoID() throws Exception {

        PartnerDTO partnerDTO = newPartner();

        mockMvc.perform(
                put("/api/v1/partners")
                .content(JSONUtil.asJsonString(partnerDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is5xxServerError());

    }


    @Test
    public void testSavingPartnerWithLongNameCauses5xxError() throws Exception {
        PartnerDTO partnerDTO = newPartner();

        partnerDTO.name = Strings.repeat("Long Arse Name", 100);

        mockMvc.perform(
                post("/api/v1/partners")
                .content(JSONUtil.asJsonString(partnerDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.code").value(InvalidEntityException.CODE));
    }


    private ResultActions savePartner(PartnerDTO partnerDTO) throws Exception {
        return mockMvc.perform(
                post("/api/v1/partners")
                        .content(JSONUtil.asJsonString(partnerDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }


    private PartnerDTO newPartner() {
        PartnerDTO partnerDTO = new PartnerDTO();

        partnerDTO.name = faker.name().fullName();
        partnerDTO.description = faker.company().name();
        partnerDTO.website = "http://partner.com";

        return partnerDTO;
    }

}
