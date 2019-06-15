package com.omarze.dto;


import java.util.List;

/**
 * created by julian
 */
public class PartnerDTO extends BaseDTO {


    public String name;

    public String description;

    private PartnerImageDTO profileImage;

    private List<PartnerImageDTO> images;

    public String website;

    private List<CampaignDTO> campaigns;


    public String getName() {
        return name;
    }

    public PartnerDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PartnerDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public PartnerDTO setWebsite(String website) {
        this.website = website;
        return this;
    }


}
