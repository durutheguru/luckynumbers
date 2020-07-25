package com.omarze.entities;


import com.julianduru.security.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * created by julian
 */
@Data
@Entity
public class LotteryUserCampaign extends BaseEntity {


    @ManyToOne
    @JoinColumn(nullable = false)
    private LotteryUser lotteryUser;


    @ManyToOne
    @JoinColumn(nullable = false)
    private Campaign campaign;


    @Column(nullable = false, length = 100)
    private String userNumber;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private LotteryUserCampaignStatus campaignStatus;


    public LotteryUserCampaign() {}


    public LotteryUserCampaign(String userNumber, Campaign campaign) {
        this.campaign = campaign;
        this.userNumber = userNumber;
    }


}
