package com.omarze.entities;


import lombok.Data;

import javax.persistence.Embeddable;

/**
 * created by julian
 */
@Data
@Embeddable
public class AspectRatio {


    private int scaleWidth;


    private int scaleHeight;


    public AspectRatio() {}


    public AspectRatio(int scaleWidth, int scaleHeight) {
        this.scaleWidth = scaleWidth;
        this.scaleHeight = scaleHeight;
    }


}