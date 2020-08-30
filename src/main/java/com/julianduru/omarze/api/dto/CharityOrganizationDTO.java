package com.julianduru.omarze.api.dto;


import com.julianduru.Constants;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * created by julian
 */
@Data
public class CharityOrganizationDTO {


    @NotEmpty(message = "Name is required")
    @Size(max = 150, message = "Maximum Name length is {max}")
    public String name;


    @NotEmpty(message = "Description is required")
    @Size(max = 250, message = "Description should not exceed {max} characters")
    public String description;


    @Pattern(regexp = Constants.Patterns.WEBSITE, message = "Website appears to be invalid")
    @Size(max = 100, message = "Maximum Website length is {max}")
    public String website;


    @NotEmpty(message = "Address is required")
    @Size(max = 250, message = "Maximum address length exceeded")
    public String address;


    public StateDTO state;


    @Size(max = 30, message = "Phone Length cannot exceed {max}")
    @Pattern(regexp = Constants.Patterns.PHONE, message = "Phone Number is invalid")
    public String phone;


    @Size(max = 100, message = "Email length cannot exceed {max}")
    @Pattern(regexp = Constants.Patterns.EMAIL, message = "Email is invalid")
    public String email;


}

