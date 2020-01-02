package com.omarze.api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.omarze.entities.*;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * created by julian
 */
@Data
public class CampaignDTO extends BaseDTO {


    @NotEmpty(message = "Campaign Name is required")
    @Size(max = 200, message = "Campaign Name should not exceed {max} characters")
    private String name;


    @Size(max = 250, message = "Campaign description should not exceed {max}")
    private String description;


    private List<CampaignImageDTO> campaignImages;


    @NotNull(message = "Partner ID is required")
    private Long partnerId;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull(message = "Campaign requires a valid Start Date")
    private LocalDate startDate;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull(message = "Campaign requires a valid End Date")
    private LocalDate endDate;


    @Valid
    @NotEmpty(message = "Stage Descriptions can not be empty")
    public List<StageDescriptionDTO> stageDescriptions;


    private Integer expectedWinnerCount;


    @NotNull(message = "Campaign Type cannot be empty")
    private CampaignType campaignType;


    private CampaignStatus campaignStatus;



}
