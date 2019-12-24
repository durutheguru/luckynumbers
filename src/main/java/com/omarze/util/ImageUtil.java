package com.omarze.util;


import com.omarze.entities.AspectRatio;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * created by julian
 */
public class ImageUtil {


    public static AspectRatio computeAspectRatio(InputStream inputStream) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        int gcd = gcd(width, height);
        width /= gcd;
        height /= gcd;

        return new AspectRatio(width, height);
    }


    private static int gcd(int num1, int num2) {
        return num2 == 0 ? num1 : gcd(num2, num1 % num2);
    }


}
