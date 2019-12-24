package com.omarze.service.partner.handlers;


import com.omarze.data.partner.PartnerDataService;
import com.omarze.entities.Partner;
import com.omarze.entities.PartnerImage;
import com.omarze.persistence.PartnerImageRepository;
import com.omarze.persistence.PartnerRepository;
import com.omarze.service.BaseServiceIntegrationTest;
import com.omarze.services.FileSaver;
import com.omarze.services.partner.handlers.UploadImages;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
    private PartnerDataService partnerDataService;


    private Partner savedPartner;


    @Before
    public void before() {
        savedPartner = partnerDataService.savePartner();
    }


    @Test
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

        Assert.assertTrue(partnerImages.size() == files.length);

        for (PartnerImage image : partnerImages) {
            Assert.assertTrue(Objects.equals(image.getPartner().getId(), savedPartner.getId()));
            Assert.assertNotNull(image.getImageKey());
            Assert.assertNotNull(image.getSavedPath());
            Assert.assertNotNull(image.getAspectRatio());
        }
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


    @After
    public void after() {
        imageRepository.deleteAll();
        partnerDataService.deletePartner(savedPartner);
    }


}

