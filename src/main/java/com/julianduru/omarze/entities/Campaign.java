package com.julianduru.omarze.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.julianduru.security.entity.BaseEntity;
import com.julianduru.omarze.exception.InvalidObjectException;
import lombok.Data;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * created by julian
 */
@Data
@Entity
public class Campaign extends BaseEntity {


    @Column(nullable = false, length = 200)
    @NotEmpty(message = "Campaign Name must be provided")
    @Size(max = 200, message = "Campaign Name should not exceed {max}")
    public String name;


    @Column(nullable = false, length = 250)
    @NotEmpty(message = "Campaign Description must be provided")
    @Size(max = 200, message = "Campaign Description should not exceed {max}")
    public String description;


    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "Campaign Partner is required")
    public Partner partner;


    @Column(nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    @NotNull(message = "Campaign must have a start date")
    public LocalDate startDate;


    @Column(nullable = false)
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    @NotNull(message = "Campaign must have an end date")
    public LocalDate endDate;


    @JsonIgnore
    @OneToMany(mappedBy = "campaign", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @NotEmpty(message = "Stage Descriptions cannot be empty")
    public List<StageDescription> stageDescriptions;


    @Column(nullable = false)
    @NotNull(message = "Expected number of winners must be provided")
    public Integer expectedWinnerCount;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    @NotNull(message = "Campaign Type must be provided")
    public CampaignType campaignType;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    public CampaignStatus campaignStatus;


    @OneToMany(mappedBy = "campaign")
    public List<CampaignStageEvaluationResult> campaignStageEvaluationResults;


    public Campaign() {}


    public Campaign addStageDescription(StageDescription stageDescription) {
        if (this.stageDescriptions == null) {
            setStageDescriptions(new ArrayList<>());
        }

        stageDescriptions.add(stageDescription);
        return this;
    }


    public Campaign initialize() throws InvalidObjectException {
        for (var description : stageDescriptions) {
            description.setCampaign(this);
        }

        setExpectedWinnerCount();
        validate();

        return this;
    }


    public boolean canBeApproved() {
        return campaignStatus == CampaignStatus.AWAITING_APPROVAL;
    }


    private void setExpectedWinnerCount() {
        var winnerCount = getFinalStageDescription().getWinnersCount();
        setExpectedWinnerCount(winnerCount);
    }


    public Stage getFinalStage() throws IllegalStateException {
        return getFinalStageDescription().getStage();
    }


    @JsonIgnore
    public StageDescription getFinalStageDescription() throws IllegalStateException {
        StageDescription.sort(stageDescriptions);
        return stageDescriptions.get(stageDescriptions.size() - 1);
    }


    public boolean isFinalStage(Stage stage) throws IllegalStateException {
        return stage == getFinalStage();
    }


    private boolean validate() throws InvalidObjectException {
        StageDescription.sort(stageDescriptions);

        validateStartEndDates();
        validateEvaluationTimes();
        validateWinnerCounts();

        return true;
    }


    private void validateStartEndDates() throws InvalidObjectException {
        var firstStageDescription = stageDescriptions.get(0);
        var lastStageDescription = stageDescriptions.get(stageDescriptions.size() - 1);

        boolean campaignStartError = firstStageDescription
                .getEvaluationTime()
                .isBefore(getEndDate().atStartOfDay());

        boolean campaignEndError = lastStageDescription
                .getEvaluationTime()
                .isBefore(firstStageDescription.getEvaluationTime());

        if (campaignStartError || campaignEndError) {
            throw new InvalidObjectException("Invalid Campaign Stage Descriptions. " +
                    "Please check the start and end dates.");
        }
    }


    private void validateEvaluationTimes() throws InvalidObjectException {
        for (int i = 0; i < stageDescriptions.size() - 1; i++) {
            if (stageDescriptions.get(i).getEvaluationTime().isAfter(stageDescriptions.get(i + 1).getEvaluationTime())) {
                throw new InvalidObjectException("Evaluation times do not follow proper order");
            }
        }
    }


    private void validateWinnerCounts() throws InvalidObjectException {
        for (int i = 0; i < stageDescriptions.size() - 1; i++) {
            if (stageDescriptions.get(i).getWinnersCount() <= stageDescriptions.get(i + 1).getWinnersCount()) {
                throw new InvalidObjectException("Invalid Campaign. Winner Counts should be progressively smaller");
            }
        }
    }


}

