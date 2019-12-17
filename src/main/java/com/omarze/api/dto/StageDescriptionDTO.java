package com.omarze.api.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omarze.entities.Stage;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * created by julian
 */
@Data
public class StageDescriptionDTO extends BaseDTO {


    @NotNull
    private Stage stage;


    @NotNull(message = "Winners Count must be a valid number")
    @Min(value = 1, message = "Winners count must be at least 1")
    private int winnersCount;


    @NotNull(message = "Evaluation Time is required")
    private LocalDateTime evaluationTime;


    @JsonIgnore
    private CampaignDTO campaign;


}
