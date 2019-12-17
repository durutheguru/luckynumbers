package com.omarze.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

/**
 * created by julian
 */
@Data
@MappedSuperclass
public class Image extends BaseEntity {


    @Column(length = 64, nullable = false)
    private String imageKey;


    @Column(length = 250, nullable = false)
    private String savedPath;


    @Column(length = 250, nullable = false)
    private String fileName;


    @Embedded
    private AspectRatio aspectRatio;




}
