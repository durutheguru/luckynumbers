package com.julianduru.omarze.util;


import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian
 */
public class ApiResponseValidator {


    public static void match(MvcResult result, Resource targetResource) throws IOException {
        String responseString = result.getResponse().getContentAsString().replaceAll("\\s+", "");
        String targetResponse = FileUtils.readFileToString(targetResource.getFile(), "UTF-8");

        assertThat(responseString).isEqualToIgnoringCase(targetResponse);
    }


}
