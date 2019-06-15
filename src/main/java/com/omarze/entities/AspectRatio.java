package com.omarze.entities;


import javax.persistence.Embeddable;

/**
 * created by julian
 */
@Embeddable
public class AspectRatio {

    private int scaleWidth;

    private int scaleHeight;


    public int getScaleWidth() {
        return scaleWidth;
    }

    public AspectRatio setScaleWidth(int scaleWidth) {
        this.scaleWidth = scaleWidth;
        return this;
    }

    public int getScaleHeight() {
        return scaleHeight;
    }

    public AspectRatio setScaleHeight(int scaleHeight) {
        this.scaleHeight = scaleHeight;
        return this;
    }


}
