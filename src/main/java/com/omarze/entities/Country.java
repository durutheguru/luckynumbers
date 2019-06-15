package com.omarze.entities;


import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * created by julian
 */
@Entity
public class Country extends BaseEntity {

    @Column(nullable = false, length = 72)
    private String name;

    public String getName() {
        return name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }


}
