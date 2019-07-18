package com.omarze.entities;


import com.omarze.util.LocalDateTimeConverter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * created by julian
 */
@Entity
public class StageDescription extends BaseEntity {


    @Enumerated(EnumType.ORDINAL)
    private Stage stage;


    @NotNull(message = "Winners Count must be a valid number")
    @Min(value = 1, message = "Winners count must be at least 1")
    @Column(nullable = false, updatable = false)
    private int winnersCount;


    @NotNull(message = "Evaluation Time is required")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime evaluationTime;


    @ManyToOne
    @JoinColumn(nullable = false)
    private Campaign campaign;


    public Stage getStage() {
        return stage;
    }


    public StageDescription setStage(Stage stage) {
        this.stage = stage;
        return this;
    }


    public int getWinnersCount() {
        return winnersCount;
    }


    public StageDescription setWinnersCount(int winnersCount) {
        this.winnersCount = winnersCount;
        return this;
    }


    public Campaign getCampaign() {
        return campaign;
    }


    public StageDescription setCampaign(Campaign campaign) {
        this.campaign = campaign;
        return this;
    }

    public LocalDateTime getEvaluationTime() {
        return evaluationTime;
    }

    public StageDescription setEvaluationTime(LocalDateTime evaluationTime) {
        this.evaluationTime = evaluationTime;
        return this;
    }


}
