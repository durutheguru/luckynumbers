package com.omarze.api.dto;


import com.omarze.Constants;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * created by julian
 */
@Data
public class PartnerDTO extends BaseDTO {


    @NotEmpty(message = "Partner Name is required")
    @Size(max = 100, message = "Partner Name should not exceed {max}")
    public String name;


    @NotEmpty(message = "Partner Description is required")
    @Size(max = 250, message = "Partner Description should not exceed {max}")
    public String description;


    private PartnerImageDTO profileImage;


    private List<PartnerImageDTO> images;


    @Size(max = 100, message = "Website Address cannot exceed {max} characters")
    @Pattern(regexp = Constants.Patterns.WEBSITE, message = "Website is invalid")
    public String website;


    private List<CampaignDTO> campaigns;


}
