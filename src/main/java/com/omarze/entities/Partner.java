package com.omarze.entities;


import com.omarze.Constants;
import com.omarze.api.annotation.DTO;
import com.omarze.dto.PartnerDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * created by julian
 */
@Entity
@DTO(PartnerDTO.class)
public class Partner extends BaseEntity {


    @NotEmpty(message = "Partner Name is required")
    @Size(max = 100, message = "Partner Name should not exceed {max}")
    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @NotEmpty(message = "Partner Description is required")
    @Size(max = 250, message = "Partner Description is required")
    @Column(length = 100, nullable = false, unique = true)
    private String description;

    @OneToOne
    private PartnerImage profileImage;

    @OneToMany(mappedBy = "partner")
    private List<PartnerImage> images;

    @Size(max = 100, message = "Website Address cannot exceed {max} characters")
    @Pattern(regexp = Constants.Patterns.WEBSITE, message = "Website is invalid")
    private String website;

    @OneToMany(mappedBy = "partner")
    private List<Campaign> campaigns;


    public String getName() {
        return name;
    }

    public Partner setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Partner setDescription(String description) {
        this.description = description;
        return this;
    }

    public PartnerImage getProfileImage() {
        return profileImage;
    }

    public Partner setProfileImage(PartnerImage profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public List<PartnerImage> getImages() {
        return images;
    }

    public Partner setImages(List<PartnerImage> images) {
        this.images = images;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public Partner setWebsite(String website) {
        this.website = website;
        return this;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public Partner setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
        return this;
    }


}
