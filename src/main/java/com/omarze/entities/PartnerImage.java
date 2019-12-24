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
public class PartnerImage extends Image {


    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Partner partner;


}
