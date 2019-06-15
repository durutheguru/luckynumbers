package com.omarze.entities;


import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * created by julian
 */
@Entity
public class Campaign extends BaseEntity {

    @NotEmpty(message = "Campaign Name is required")
    @Size(max = 200, message = "Campaign Name should not exceed {max} characters")
    @Column(nullable = false, length = 200)
    private String name;

    @Size(max = 250, message = "Campaign description should not exceed {max}")
    @Column(nullable = false, length = 250)
    private String description;

    @OneToMany(mappedBy = "campaign", cascade = {CascadeType.ALL})
    private List<CampaignImage> campaignImages;

    @OneToMany(mappedBy = "campaign", cascade = {CascadeType.ALL})
    private List<SubCampaign> subCampaigns;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Partner partner;

    @Column(nullable = false)
    @NotEmpty(message = "Campaign requires a valid Start Date")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate startDate;

    @Column(nullable = false)
    @NotEmpty(message = "Campaign requires a valid End Date")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate endDate;

    @ElementCollection
    @JoinTable(name = "CampaignStageDescriptors", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "StageDescriptors", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<StageDescriptor> stageDescriptors;

    @Column(nullable = false)
    @NotNull(message = "Number of expected Winners cannot be empty")
    private Integer expectedWinnerCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 60)
    private RequestStatus requestStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    @NotEmpty(message = "Campaign Type cannot be empty")
    private CampaignType campaignType;


    public String getName() {
        return name;
    }

    public Campaign setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Campaign setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<CampaignImage> getCampaignImages() {
        return campaignImages;
    }

    public Campaign setCampaignImages(List<CampaignImage> campaignImages) {
        this.campaignImages = campaignImages;
        return this;
    }

    public List<SubCampaign> getSubCampaigns() {
        return subCampaigns;
    }

    public Campaign setSubCampaigns(List<SubCampaign> subCampaigns) {
        this.subCampaigns = subCampaigns;
        return this;
    }

    public Partner getPartner() {
        return partner;
    }

    public Campaign setPartner(Partner partner) {
        this.partner = partner;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Campaign setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Campaign setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public List<StageDescriptor> getStageDescriptors() {
        return stageDescriptors;
    }

    public Campaign setStageDescriptors(List<StageDescriptor> stageDescriptors) {
        this.stageDescriptors = stageDescriptors;
        return this;
    }

    public Integer getExpectedWinnerCount() {
        return expectedWinnerCount;
    }

    public Campaign setExpectedWinnerCount(Integer expectedWinnerCount) {
        this.expectedWinnerCount = expectedWinnerCount;
        return this;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public Campaign setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public CampaignType getCampaignType() {
        return campaignType;
    }

    public Campaign setCampaignType(CampaignType campaignType) {
        this.campaignType = campaignType;
        return this;
    }


}
