package com.omarze.entities;


import com.julianduru.security.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import static com.omarze.Constants.*;

/**
 * created by julian
 */
@Data
@Entity
public class CharityOrganization extends BaseEntity {


    @Column(length = 150, nullable = false)
    private String name;


    @Column(length = 250, nullable = false)
    private String description;


    @Column(length = 100, nullable = false)
    private String website;


    @Column(length = 250, nullable = false)
    private String address;


    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;


    @Column(length = 30)
    private String phone;


    @Column(length = 100)
    private String email;


}
