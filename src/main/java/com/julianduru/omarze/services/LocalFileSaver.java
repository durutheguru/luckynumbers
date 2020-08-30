package com.julianduru.omarze.services;


import com.julianduru.util.FileUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * created by julian
 */
@Component
public class LocalFileSaver implements FileSaver {


    @Value("${lottery.config.file-root-path}")
    private String fileRootPath;


    @Override
    public String saveFile(String relativePath, byte[] fileBytes) throws IOException {
        String fullPath = FileUtil.fullPath(fileRootPath, relativePath);
        FileUtil.makeDirectories(fullPath);

        IOUtils.copy(new ByteArrayInputStream(fileBytes), new FileOutputStream(fullPath));

        return fullPath;
    }


    @Override
    public String saveFile(String relativePath, InputStream inputStream) throws IOException {
        String fullPath = FileUtil.fullPath(fileRootPath, relativePath);
        FileUtil.makeDirectories(fullPath);

        IOUtils.copy(inputStream, new FileOutputStream(fullPath));

        return fullPath;
    }


    @Override
    public InputStream readFile(String path) throws IOException {
        return new FileInputStream(path);
    }


}
