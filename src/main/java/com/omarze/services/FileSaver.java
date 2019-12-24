package com.omarze.services;


import java.io.IOException;
import java.io.InputStream;

/**
 * created by julian
 */
public interface FileSaver {


    String saveFile(String relativePath, byte[] fileBytes) throws IOException;


    String saveFile(String relativePath, InputStream inputStream) throws IOException;


    InputStream readFile(String path) throws IOException;


}


