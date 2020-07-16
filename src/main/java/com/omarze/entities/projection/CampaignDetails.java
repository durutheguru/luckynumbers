package com.omarze.entities.projection;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.omarze.entities.Campaign;
import com.omarze.entities.CampaignStatus;
import com.omarze.entities.CampaignType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;

/**
 * created by julian
 */
@Projection(
    name = "campaignDetails",
    types = {
        Campaign.class
    }
)
public interface CampaignDetails {


    Long getId();


    String getName();


    String getDescription();


    @Value("#{target.partner.name}")
    String getPartnerName();


    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate getStartDate();


    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate getEndDate();


    Integer getExpectedWinnerCount();


    CampaignType getCampaignType();


    CampaignStatus getCampaignStatus();


}
