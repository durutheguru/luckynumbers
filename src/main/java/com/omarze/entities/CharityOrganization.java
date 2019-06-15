package com.omarze.entities;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import static com.omarze.Constants.*;

/**
 * created by julian
 */
@Entity
public class CharityOrganization extends BaseEntity {

    @NotEmpty(message = "Name is required")
    @Size(max = 150, message = "Maximum Name length is {max}")
    @Column(length = 150, nullable = false)
    private String name;

    @NotEmpty(message = "Description is required")
    @Size(max = 250, message = "Description should not exceed {max} characters")
    @Column(length = 250, nullable = false)
    private String description;

    @Pattern(regexp = Patterns.WEBSITE, message = "Website appears to be invalid")
    @Size(max = 100, message = "Maximum Website length is {max}")
    private String website;

    @NotEmpty(message = "Address is required")
    @Size(max = 250, message = "Maximum address length exceeded")
    @Column(length = 250, nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @Column(length = 30)
    @Size(max = 30, message = "Phone Length cannot exceed {max}")
    @Pattern(regexp = Patterns.PHONE, message = "Phone Number is invalid")
    private String phone;

    @Column(length = 100)
    @Size(max = 100, message = "Email length cannot exceed {max}")
    @Pattern(regexp = Patterns.EMAIL, message = "Email is invalid")
    private String email;


    public String getPhone() {
        return phone;
    }

    public CharityOrganization setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CharityOrganization setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public CharityOrganization setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CharityOrganization setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public CharityOrganization setWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CharityOrganization setAddress(String address) {
        this.address = address;
        return this;
    }

    public State getState() {
        return state;
    }

    public CharityOrganization setState(State state) {
        this.state = state;
        return this;
    }


}
