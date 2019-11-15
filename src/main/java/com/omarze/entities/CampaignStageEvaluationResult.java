package com.omarze.entities;


import javax.persistence.*;
import java.util.List;

/**
 * created by julian
 */
@Entity
public class CampaignStageEvaluationResult extends BaseEntity {


    @ManyToOne
    @JoinColumn(nullable = false)
    private Campaign campaign;


    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private Stage stage;


    @ElementCollection
    private List<String> winningNumbers;


    public Campaign getCampaign() {
        return campaign;
    }

    public CampaignStageEvaluationResult setCampaign(Campaign campaign) {
        this.campaign = campaign;
        return this;
    }

    public Stage getStage() {
        return stage;
    }

    public CampaignStageEvaluationResult setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public List<String> getWinningNumbers() {
        return winningNumbers;
    }

    public CampaignStageEvaluationResult setWinningNumbers(List<String> winningNumbers) {
        this.winningNumbers = winningNumbers;
        return this;
    }


    public boolean isFinalStage() {
        return campaign.isFinalStage(stage);
    }


}
