package com.julianduru.omarze.api.dto;


import com.julianduru.util.entity.AspectRatio;
import lombok.Data;

/**
 * created by julian
 */
@Data
public class ImageDTO extends BaseDTO {


    private String imageKey;


    private String savedPath;


    private String fileName;


    private AspectRatio aspectRatio;


}
