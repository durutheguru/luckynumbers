package com.julianduru.omarze.entities;


import com.julianduru.security.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * created by julian
 */
@Data
@Entity
public class State extends BaseEntity {


    @Column(nullable = false, length = 72)
    private String name;


    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;


}
