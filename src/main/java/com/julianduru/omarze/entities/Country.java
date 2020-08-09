package com.julianduru.omarze.entities;


import com.julianduru.security.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * created by julian
 */
@Data
@Entity
public class Country extends BaseEntity {


    @Column(nullable = false, length = 72)
    private String name;


}
