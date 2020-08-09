package com.julianduru.omarze.entities.projection;


import com.julianduru.omarze.entities.Partner;
import org.springframework.data.rest.core.config.Projection;

/**
 * created by julian
 */
@Projection(
    name = "partnerMinDetails",
    types = {
        Partner.class
    }
)
public interface PartnerDetails {


    Long getId();


    String getName();


}
