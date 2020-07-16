package com.omarze.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * created by julian
 */
@Data
@Entity
public class Partner extends BaseEntity {


    @Column(length = 100, nullable = false, unique = true)
    @NotEmpty(message = "Partner name is required")
    @Size(max = 100, message = "Partner name should not exceed {max}")
    private String name;


    @Column(length = 250, nullable = false, unique = true)
    @NotEmpty(message = "Partner description is required")
    private String description;


    @OneToOne
    private PartnerImage profileImage;


    @OneToMany(mappedBy = "partner")
    private List<PartnerImage> images;


    @Column(length = 100)
    private String website;


    @JsonIgnore
    @OneToMany(mappedBy = "partner")
    private List<Campaign> campaigns;



}
