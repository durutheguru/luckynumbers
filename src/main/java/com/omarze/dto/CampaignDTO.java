package com.omarze.dto;


import com.omarze.entities.CampaignType;
import com.omarze.entities.RequestStatus;
import com.omarze.entities.StageDescriptor;

import java.time.LocalDate;
import java.util.List;

/**
 * created by julian
 */
public class CampaignDTO extends BaseDTO {

    private String name;

    private String description;

    private List<CampaignImageDTO> campaignImages;

    private List<SubCampaignDTO> subCampaigns;

    private PartnerDTO partner;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<StageDescriptor> stageDescriptors;

    private Integer expectedWinnerCount;

    private RequestStatus requestStatus;

    private CampaignType campaignType;


    public String getName() {
        return name;
    }

    public CampaignDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CampaignDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<CampaignImageDTO> getCampaignImages() {
        return campaignImages;
    }

    public CampaignDTO setCampaignImages(List<CampaignImageDTO> campaignImages) {
        this.campaignImages = campaignImages;
        return this;
    }

    public List<SubCampaignDTO> getSubCampaigns() {
        return subCampaigns;
    }

    public CampaignDTO setSubCampaigns(List<SubCampaignDTO> subCampaigns) {
        this.subCampaigns = subCampaigns;
        return this;
    }

    public PartnerDTO getPartner() {
        return partner;
    }

    public CampaignDTO setPartner(PartnerDTO partner) {
        this.partner = partner;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public CampaignDTO setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public CampaignDTO setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public List<StageDescriptor> getStageDescriptors() {
        return stageDescriptors;
    }

    public CampaignDTO setStageDescriptors(List<StageDescriptor> stageDescriptors) {
        this.stageDescriptors = stageDescriptors;
        return this;
    }

    public Integer getExpectedWinnerCount() {
        return expectedWinnerCount;
    }

    public CampaignDTO setExpectedWinnerCount(Integer expectedWinnerCount) {
        this.expectedWinnerCount = expectedWinnerCount;
        return this;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public CampaignDTO setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public CampaignType getCampaignType() {
        return campaignType;
    }

    public CampaignDTO setCampaignType(CampaignType campaignType) {
        this.campaignType = campaignType;
        return this;
    }


}
