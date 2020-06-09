package com.omarze.rest;


import com.google.common.base.Strings;
import com.omarze.controller.BaseControllerTest;
import com.omarze.controller.api.PartnerController;
import com.omarze.data.partner.PartnerDataProvider;
import com.omarze.entities.BackOfficeUser;
import com.omarze.entities.Partner;
import com.omarze.persistence.PartnerRepository;
import com.omarze.util.JSONUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * created by julian
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql({"/db/scripts/partner/init.sql"})
@WithMockUser(username = BaseControllerTest.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
public class PartnerResourceTest extends BaseControllerTest {


    @Autowired
    private PartnerDataProvider partnerDataProvider;


    @Autowired
    private PartnerRepository partnerRepository;



    @Test
    public void testSavingPartner() throws Exception {
        Partner partner = partnerDataProvider.newPartner();

        mockMvc.perform(
            post(PartnerController.PATH)
                .content(JSONUtil.asJsonString(partner))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void testSavingAlreadyExistingPartnerName() throws Exception {
        Partner partner = partnerRepository.findFirstBy().get();

        Partner newPartner = partnerDataProvider.newPartner();
        newPartner.setName(partner.getName());


        mockMvc.perform(
            post(PartnerController.PATH)
                .content(JSONUtil.asJsonString(newPartner))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().is5xxServerError());
    }


    @Test
    public void testLoadingPartners() throws Exception {
        mockMvc.perform(
            get(PartnerController.PATH)
        ).andDo(print())
            .andExpect(status().is2xxSuccessful());
    }


//    @Test
//    public void testUpdatingPartner() throws Exception {
//
//        PartnerDTO partnerDTO = partnerDataService.newPartnerDTO();
//
//        String savedPartnerResponse = mockMvc.perform(
//                post(PartnerController.PATH)
//                .content(JSONUtil.asJsonString(partnerDTO))
//                .contentType(MediaType.APPLICATION_JSON)
//        ).andDo(MockMvcResultHandlers.print())
//                .andExpect(status().is2xxSuccessful())
//                .andReturn().getResponse().getContentAsString();
//
//        mockMvc.perform(
//                put(PartnerController.PATH)
//                .content(new JSONObject((LinkedHashMap)JsonPath.read(savedPartnerResponse, "$")).toString())
//                .contentType(MediaType.APPLICATION_JSON)
//        ).andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk());
//    }


    @Test
    public void testSavingPartnerWithLongNameCausesError() throws Exception {
        Partner partner = partnerDataProvider.newPartner();
        partner.setName(Strings.repeat("Long Arse Name", 100));

        mockMvc.perform(
            post(PartnerController.PATH)
                .content(JSONUtil.asJsonString(partner))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().is4xxClientError());
    }


    @Test
    public void testUploadingPartnerImages() throws Exception {
        Partner partner = partnerDataProvider.savePartner();

        MockMultipartFile file0 = new MockMultipartFile("files", "img0.jpg", MediaType.APPLICATION_OCTET_STREAM_VALUE, new ClassPathResource("img/img0.jpg").getInputStream());
        MockMultipartFile file2 = new MockMultipartFile("files", "img2.jpg", MediaType.APPLICATION_OCTET_STREAM_VALUE, new ClassPathResource("img/img2.jpg").getInputStream());

        mockMvc.perform(
            multipart(PartnerController.PATH + "/" + partner.getId() + "/image")
                .file(file0)
                .file(file2)
        ).andDo(print())
            .andExpect(status().is2xxSuccessful());
    }


}


