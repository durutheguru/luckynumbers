package com.omarze.entities;


import com.julianduru.security.entity.BaseEntity;
import com.julianduru.util.jpa.LocalDateTimeConverter;
import com.omarze.security.annotation.CanWriteCampaign;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * created by julian
 */
@Data
@Entity
@CanWriteCampaign
public class StageDescription extends BaseEntity {


    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Stage stage;


    @NotNull(message = "Winners Count must be a valid number")
    @Min(value = 1, message = "Winners count must be at least 1")
    @Column(nullable = false, updatable = false)
    private int winnersCount;


    @NotNull(message = "Evaluation Time is required")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime evaluationTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Campaign campaign;


    public StageDescription() { }


    public StageDescription (
        Stage stage,
        int winnersCount,
        LocalDateTime evaluationTime
    ) {
        this.stage = stage;
        this.winnersCount = winnersCount;
        this.evaluationTime = evaluationTime;
    }


    public boolean hasWinnings() {
        return campaign.isFinalStage(stage);
    }


    public static void sort(List<StageDescription> stageDescriptions) {
        if (stageDescriptions == null || stageDescriptions.isEmpty()) {
            throw new IllegalStateException("Cannot process Stage Descriptions. Data is empty");
        }

        stageDescriptions.sort((s1, s2) -> s1.getStage().compareTo(s2.getStage()));
    }


}


