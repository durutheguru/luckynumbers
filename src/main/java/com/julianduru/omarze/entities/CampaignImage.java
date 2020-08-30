package com.julianduru.omarze.entities;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * created by julian
 */
@Entity
public class CampaignImage extends Image {


    @ManyToOne
    @JoinColumn(nullable = false)
    private Campaign campaign;

    @ManyToOne
    private SubCampaign subCampaign;


    public Campaign getCampaign() {
        return campaign;
    }

    public CampaignImage setCampaign(Campaign campaign) {
        this.campaign = campaign;
        return this;
    }

    public SubCampaign getSubCampaign() {
        return subCampaign;
    }

    public CampaignImage setSubCampaign(SubCampaign subCampaign) {
        this.subCampaign = subCampaign;
        return this;
    }


}
