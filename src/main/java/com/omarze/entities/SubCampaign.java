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

    @ManyToOne
    @JoinColumn(nullable = false)
    private Campaign campaign;

    @OneToMany(mappedBy = "subCampaign", cascade = {CascadeType.ALL})
    private List<CampaignImage> campaignImages;

    @ElementCollection
    private List<Integer> winningNumbers;

    @Column(nullable = false)
    @NotNull(message = "Number of expected Winners cannot be empty")
    private Integer expectedWinnerCount;


    public String getDescription() {
        return description;
    }

    public SubCampaign setDescription(String description) {
        this.description = description;
        return this;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public SubCampaign setCampaign(Campaign campaign) {
        this.campaign = campaign;
        return this;
    }

    public List<CampaignImage> getCampaignImages() {
        return campaignImages;
    }

    public SubCampaign setCampaignImages(List<CampaignImage> campaignImages) {
        this.campaignImages = campaignImages;
        return this;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public SubCampaign setWinningNumbers(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
        return this;
    }

    public Integer getExpectedWinnerCount() {
        return expectedWinnerCount;
    }

    public SubCampaign setExpectedWinnerCount(Integer expectedWinnerCount) {
        this.expectedWinnerCount = expectedWinnerCount;
        return this;
    }


}
