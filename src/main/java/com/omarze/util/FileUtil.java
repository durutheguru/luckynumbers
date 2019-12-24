package com.omarze.util;


import java.io.File;
import java.io.IOException;

/**
 * created by julian
 */
public class FileUtil {


    public static String fullPath(String root, String relative) {
        return root + (relative.startsWith(File.separator) ? relative : (File.separator + relative));
    }


    public static String getExtension(String fileName) {
        int dotIndex = fileName.indexOf(".");
        return dotIndex == -1 ? fileName : fileName.substring(dotIndex + 1);
    }


    public static void makeDirectories(String fullPath) throws IOException {
        int fileIndex = fullPath.lastIndexOf(File.separator);

        if (fileIndex == -1) {
            throw new IOException("Cannot create directories, no path separator was found");
        }

        String dirPaths = fullPath.substring(0, fileIndex);
        File pathsDir = new File(dirPaths);

        if (!pathsDir.exists() && !pathsDir.mkdirs()) {
            throw new IOException("Error occurred while creating parent directories");
        }
    }


}
