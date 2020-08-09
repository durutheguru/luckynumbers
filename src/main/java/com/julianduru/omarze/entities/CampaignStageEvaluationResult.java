package com.julianduru.omarze.entities;


import com.julianduru.security.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * created by julian
 */
@Data
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


    @Transient
    private List<LotteryUserCampaign> userCampaigns;


    public boolean isFinalStage() {
        return campaign.isFinalStage(stage);
    }


}
