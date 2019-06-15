package com.omarze.controller;


import com.omarze.dto.PartnerDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        mockMvc.perform(
                post("/api/v1/partner")
                .content(asJsonString(partnerDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }


    private PartnerDTO newPartner() {
        PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.name = "Partner";
        partnerDTO.description = "Partner Description";
        partnerDTO.website = "http://partner.com";

        return partnerDTO;
    }

}
