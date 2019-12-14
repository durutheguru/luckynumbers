package com.omarze.entities;


import com.omarze.util.LocalDateTimeConverter;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * created by julian
 */
@Data
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


    @OneToOne
    private SubCampaign subCampaign;


    @ManyToOne
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
        return campaign.isFinalStage(stage) || subCampaign != null;
    }


}


