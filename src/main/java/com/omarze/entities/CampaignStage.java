package com.omarze.entities;


import javax.persistence.*;
import java.util.List;

/**
 * created by julian
 */
@Entity
public class CampaignStage extends BaseEntity {


    @ManyToOne
    @JoinColumn(nullable = false)
    private Campaign campaign;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private StageDescriptor stageDescriptor;

    @ElementCollection
    private List<Integer> winningNumbers;


    public Campaign getCampaign() {
        return campaign;
    }

    public CampaignStage setCampaign(Campaign campaign) {
        this.campaign = campaign;
        return this;
    }

    public StageDescriptor getStageDescriptor() {
        return stageDescriptor;
    }

    public CampaignStage setStageDescriptor(StageDescriptor stageDescriptor) {
        this.stageDescriptor = stageDescriptor;
        return this;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public CampaignStage setWinningNumbers(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
        return this;
    }


}
