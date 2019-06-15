package com.omarze.entities;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * created by julian
 */
@Entity
public class PartnerImage extends Image {

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Partner partner;


    public Partner getPartner() {
        return partner;
    }

    public PartnerImage setPartner(Partner partner) {
        this.partner = partner;
        return this;
    }


}
