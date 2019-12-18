package com.omarze.util;


import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * created by julian
 */
public class CryptoUtil {


    public static int hashBytes(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(bytes);
            return ByteBuffer.wrap(md.digest()).getInt();
        }
        catch (NoSuchAlgorithmException e) {
            return 0;
        }
    }


}
