package com.omarze.dto;


import com.omarze.entities.AspectRatio;

/**
 * created by julian
 */
public class ImageDTO extends BaseDTO {


    private String imageKey;


    private String savedPath;


    private String fileName;


    private AspectRatio aspectRatio;



    public String getImageKey() {
        return imageKey;
    }

    public ImageDTO setImageKey(String imageKey) {
        this.imageKey = imageKey;
        return this;
    }

    public String getSavedPath() {
        return savedPath;
    }

    public ImageDTO setSavedPath(String savedPath) {
        this.savedPath = savedPath;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public ImageDTO setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public AspectRatio getAspectRatio() {
        return aspectRatio;
    }

    public ImageDTO setAspectRatio(AspectRatio aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }


}
