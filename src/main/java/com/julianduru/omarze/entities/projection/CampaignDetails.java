package com.julianduru.omarze.entities.projection;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.entities.CampaignStageEvaluationResult;
import com.julianduru.omarze.entities.CampaignStatus;
import com.julianduru.omarze.entities.CampaignType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.util.List;

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


    List<CampaignStageEvaluationResult> getCampaignStageEvaluationResults();


}
