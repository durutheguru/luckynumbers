package com.omarze.entities;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by julian
 */
@Data
@Entity
public class Campaign extends BaseEntity {


    @Column(nullable = false, length = 200)
    public String name;


    @Column(nullable = false, length = 250)
    public String description;


    @OneToMany(mappedBy = "campaign", cascade = {CascadeType.ALL})
    public List<CampaignImage> campaignImages;


    @ManyToOne
    @JoinColumn(nullable = false)
    public Partner partner;


    @Column(nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    public LocalDate startDate;


    @Column(nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    public LocalDate endDate;


    @OneToMany(mappedBy = "campaign", cascade = {CascadeType.ALL}, orphanRemoval = true)
    public List<StageDescription> stageDescriptions;


    @Column(nullable = false)
    public Integer expectedWinnerCount;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 60)
    public RequestStatus requestStatus;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    public CampaignType campaignType;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    public CampaignStatus campaignStatus;


    public Campaign() {}


    @Builder
    public Campaign(
        String name,
        String description,
        List<CampaignImage> campaignImages,
        Partner partner,
        LocalDate startDate,
        LocalDate endDate,
        List<StageDescription> stageDescriptions,
        Integer expectedWinnerCount,
        RequestStatus requestStatus,
        CampaignType campaignType,
        CampaignStatus campaignStatus
    ) {
        this.name = name;
        this.description = description;
        this.campaignImages = campaignImages;
        this.partner = partner;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stageDescriptions = stageDescriptions;
        this.expectedWinnerCount = expectedWinnerCount;
        this.requestStatus = requestStatus;
        this.campaignType = campaignType;
        this.campaignStatus = campaignStatus;
    }



    public Campaign addStageDescription(StageDescription stageDescription) {
        if (this.stageDescriptions == null) {
            setStageDescriptions(new ArrayList<>());
        }

        stageDescriptions.add(stageDescription);
        return this;
    }


    public Campaign initialize() {
        for (StageDescription description : stageDescriptions) {
            description.setCampaign(this);
        }

        return this;
    }


    public boolean canBeApproved() {
        return requestStatus == RequestStatus.PENDING && campaignStatus == CampaignStatus.AWAITING_APPROVAL;
    }


    public Stage getFinalStage() throws IllegalStateException {
        if (stageDescriptions == null) {
            throw new IllegalStateException("Cannot evaluate Final stage without Stage Descriptions");
        }

        List<Stage> stages = stageDescriptions
                .stream().map(StageDescription::getStage)
                .collect(Collectors.toList());

        stages.sort(Enum::compareTo);

        return stages.get(stages.size() - 1);
    }


    public boolean isFinalStage(Stage stage) throws IllegalStateException {
        return stage == getFinalStage();
    }



}
