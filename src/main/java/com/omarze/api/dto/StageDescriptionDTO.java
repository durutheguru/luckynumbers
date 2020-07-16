package com.omarze.api.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.omarze.entities.Stage;
import com.omarze.util.json.LocalDateTimeDeserializer;
import com.omarze.util.json.LocalDateTimeSerializer;
import lombok.Data;
import lombok.ToString;

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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime evaluationTime;


    @JsonIgnore
    @ToString.Exclude
    private CampaignDTO campaign;


}

