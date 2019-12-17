package com.omarze.api.dto;


import com.omarze.entities.AspectRatio;
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
