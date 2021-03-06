package com.julianduru.omarze.service.partner.handlers;


import com.julianduru.omarze.data.partner.PartnerDataProvider;
import com.julianduru.omarze.entities.Partner;
import com.julianduru.omarze.entities.PartnerImage;
import com.julianduru.omarze.persistence.PartnerImageRepository;
import com.julianduru.omarze.persistence.PartnerRepository;
import com.julianduru.omarze.service.BaseServiceIntegrationTest;
import com.julianduru.omarze.services.FileSaver;
import com.julianduru.omarze.services.partner.handlers.UploadImages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian
 */
public class UploadImagesTest extends BaseServiceIntegrationTest {


    private String[] imgResourcePaths = new String[] {
        "img/img0.jpg", "img/img1.png", "img/img2.jpg"
    };


    @Autowired
    private FileSaver fileSaver;


    @Autowired
    private PartnerRepository partnerRepository;


    @Autowired
    private PartnerImageRepository imageRepository;


    @Autowired
    private PartnerDataProvider partnerDataProvider;


    private Partner savedPartner;


    @BeforeEach
    public void before() {
        savedPartner = partnerDataProvider.save();
    }


    @Test
    @Disabled
    public void test_uploading_multiple_images() throws Exception {
        MultipartFile[] files = loadMultipartFiles();

        List<PartnerImage> partnerImages = UploadImages.builder()
                .files(files)
                .fileSaver(fileSaver)
                .imageRepository(imageRepository)
                .partnerId(savedPartner.getId())
                .partnerRepository(partnerRepository)
                .build()
                .execute();

        assertThat(partnerImages.size()).isEqualTo(files.length);

        assertThat(partnerImages)
            .allSatisfy(
                image -> {
                    assertThat(image.getPartner().getId()).isEqualTo(savedPartner.getId());
                    assertThat(image.getImageKey()).isNotBlank();
                    assertThat(image.getSavedPath()).isNotBlank();
                    assertThat(image.getAspectRatio()).isNotNull();
                }
            );
    }


    private MultipartFile[] loadMultipartFiles() throws IOException {
        MultipartFile[] files = new MultipartFile[imgResourcePaths.length];

        ClassPathResource resource;
        for (int i = 0; i < files.length; i++) {
            resource = new ClassPathResource(imgResourcePaths[i]);
            files[i] = new MockMultipartFile("file", resource.getFilename(), MediaType.APPLICATION_OCTET_STREAM_VALUE, resource.getInputStream());
        }

        return files;
    }


    @AfterEach
    public void after() {
        imageRepository.deleteAll();
        partnerDataProvider.deletePartner(savedPartner);
    }


}

