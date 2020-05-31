package com.omarze.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * created by julian
 */
@Data
@MappedSuperclass
public class Image extends BaseEntity {


    @Column(length = 64, nullable = false)
    @NotEmpty(message = "Image Key is required")
    @Size(max = 64, message = "Image Key length should not exceed {max}")
    private String imageKey;


    @Column(length = 250, nullable = false)
    @NotEmpty(message = "Image Saved Path is required")
    @Size(max = 250, message = "Saved Path length should not exceed {max}")
    private String savedPath;


    @Column(length = 250, nullable = false)
    @Size(max = 250, message = "File Name should not exceed {max}")
    @NotEmpty(message = "Image File Name is required")
    private String fileName;


    @Embedded
    private AspectRatio aspectRatio;


}

