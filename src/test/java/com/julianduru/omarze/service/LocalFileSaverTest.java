package com.julianduru.omarze.service;


import com.julianduru.omarze.services.LocalFileSaver;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian
 */
@Slf4j
public class LocalFileSaverTest extends BaseServiceIntegrationTest {


    private static final byte[] TEST_BYTES = new byte[]{0x0, 0x0, 0x1};


    private static final String TEST_RELATIVE_PATH = "/relative/file.png";
    

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
            assertThat(content[i]).isEqualTo(TEST_BYTES[i]);
        }
    }


    private void runTests(String fullPath) {
        assertThat(fullPath).isNotBlank();
        assertThat(Files.exists(Paths.get(fullPath))).isTrue();

        deleteFile(fullPath);
    }


    public void deleteFile(String fullPath) {
        try {
            Files.deleteIfExists(Paths.get(fullPath));
        }
        catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }


}

