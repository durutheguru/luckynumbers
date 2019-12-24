package com.omarze.service;


import com.omarze.services.LocalFileSaver;
import com.omarze.util.AppLogger;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * created by julian
 */
public class LocalFileSaverTest extends BaseServiceIntegrationTest {


    private final static byte[] TEST_BYTES = new byte[]{0x0, 0x0, 0x1};


    private final static String TEST_RELATIVE_PATH = "/relative/file.png";
    

    @Autowired
    private LocalFileSaver localFileSaver;



    @Test
    public void test_saving_local_file_bytes() throws Exception {
        String fullPath = localFileSaver.saveFile(TEST_RELATIVE_PATH, TEST_BYTES);

        runTests(fullPath);
    }


    @Test
    public void test_saving_local_file_inputstream() throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(TEST_BYTES);

        String fullPath = localFileSaver.saveFile(TEST_RELATIVE_PATH, inputStream);

        runTests(fullPath);
    }


    @Test
    public void test_reading_local_file_bytes() throws Exception {
        String fullPath = localFileSaver.saveFile(TEST_RELATIVE_PATH, TEST_BYTES);

        InputStream inputStream = localFileSaver.readFile(fullPath);

        byte[] content = new byte[1024];
        IOUtils.read(inputStream, content);

        for (int i = 0, l = TEST_BYTES.length; i < l; i++) {
            Assert.assertTrue(content[i] == TEST_BYTES[i]);
        }
    }


    private void runTests(String fullPath) {
        Assert.assertNotNull(fullPath);
        Assert.assertTrue(Files.exists(Paths.get(fullPath)));

        deleteFile(fullPath);
    }


    public void deleteFile(String fullPath) {
        try {
            Files.deleteIfExists(Paths.get(fullPath));
        }
        catch (IOException e) {
            AppLogger.error(e);
        }
    }


}
