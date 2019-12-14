package com.omarze.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * created by julian
 */
@Data
@Entity
public class PartnerUser extends ApplicationUser {


    @ManyToOne
    @JoinColumn(nullable = false)
    private Partner partner;



}
