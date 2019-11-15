package com.omarze.entities;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * created by julian
 */
@Entity
public class SubCampaign extends BaseEntity {


    @NotEmpty(message = "Description cannot be empty")
    @Column(length = 250, nullable = false)
    @Size(max = 250, message = "SubCampaign description should not exceed {max}")
    private String description;

    @OneToMany(mappedBy = "subCampaign", cascade = {CascadeType.ALL})
    private List<CampaignImage> campaignImages;


    public String getDescription() {
        return description;
    }

    public SubCampaign setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<CampaignImage> getCampaignImages() {
        return campaignImages;
    }

    public SubCampaign setCampaignImages(List<CampaignImage> campaignImages) {
        this.campaignImages = campaignImages;
        return this;
    }


}
