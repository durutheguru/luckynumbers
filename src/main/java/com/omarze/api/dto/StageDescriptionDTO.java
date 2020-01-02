package com.omarze.api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omarze.entities.Stage;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Integer winnersCount;


    @NotNull(message = "Evaluation Time is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime evaluationTime;


    @JsonIgnore
    @ToString.Exclude
    private CampaignDTO campaign;


}
