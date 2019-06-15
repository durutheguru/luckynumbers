package com.omarze.entities;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

/**
 * created by julian
 */
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


    public String getImageKey() {
        return imageKey;
    }

    public Image setImageKey(String imageKey) {
        this.imageKey = imageKey;
        return this;
    }

    public String getSavedPath() {
        return savedPath;
    }

    public Image setSavedPath(String savedPath) {
        this.savedPath = savedPath;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public Image setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public AspectRatio getAspectRatio() {
        return aspectRatio;
    }

    public Image setAspectRatio(AspectRatio aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }


}
