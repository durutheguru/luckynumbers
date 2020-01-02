package com.omarze.api.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omarze.Constants;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * created by julian
 */
@Data
public class ApplicationUserDTO extends BaseDTO {


    @NotEmpty(message = "Name is required")
    @Size(max = 100, message = "Maximum Name length is {max}")
    private String name;


    @Size(max = 100, message = "Email length cannot exceed {max}")
    @Pattern(regexp = Constants.Patterns.EMAIL, message = "Email is invalid")
    private String email;


    @NotEmpty(message = "Username is required")
    @Size(max = 100, message = "Username should not exceed {max} characters")
    private String username;



    @NotEmpty(message = "Password is required")
    @Size(max = 50, message = "Password length is too long")
    private String passwordIn;


}

