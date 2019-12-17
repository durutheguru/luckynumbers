package com.omarze.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * created by julian
 */
@Data
@Entity
public class Partner extends BaseEntity {


    @Column(length = 100, nullable = false, unique = true)
    private String name;


    @Column(length = 250, nullable = false, unique = true)
    private String description;


    @OneToOne
    private PartnerImage profileImage;


    @OneToMany(mappedBy = "partner")
    private List<PartnerImage> images;


    @Column(length = 100)
    private String website;


    @OneToMany(mappedBy = "partner")
    private List<Campaign> campaigns;



}
