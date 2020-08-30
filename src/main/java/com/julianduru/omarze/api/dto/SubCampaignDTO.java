package com.julianduru.omarze.api.dto;


import java.util.List;

/**
 * created by julian
 */
public class SubCampaignDTO extends BaseDTO {


    private String description;


    private List<CampaignImageDTO> campaignImages;


    private List<Integer> winningNumbers;


    private Integer expectedWinnerCount;


    public String getDescription() {
        return description;
    }

    public SubCampaignDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<CampaignImageDTO> getCampaignImages() {
        return campaignImages;
    }

    public SubCampaignDTO setCampaignImages(List<CampaignImageDTO> campaignImages) {
        this.campaignImages = campaignImages;
        return this;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public SubCampaignDTO setWinningNumbers(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
        return this;
    }

    public Integer getExpectedWinnerCount() {
        return expectedWinnerCount;
    }

    public SubCampaignDTO setExpectedWinnerCount(Integer expectedWinnerCount) {
        this.expectedWinnerCount = expectedWinnerCount;
        return this;
    }


}
