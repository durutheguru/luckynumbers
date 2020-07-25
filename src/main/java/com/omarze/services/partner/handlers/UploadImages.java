package com.omarze.services.partner.handlers;


import com.julianduru.util.FileUtil;
import com.julianduru.util.ImageUtil;
import com.julianduru.util.entity.AspectRatio;
import com.omarze.entities.Partner;
import com.omarze.entities.PartnerImage;
import com.omarze.exception.EntityNotFoundException;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.PartnerImageRepository;
import com.omarze.persistence.PartnerRepository;
import com.omarze.services.CommandBase;
import com.omarze.services.FileSaver;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * created by julian
 */
public class UploadImages extends CommandBase<List<PartnerImage>> {


    private static AtomicLong counter = new AtomicLong(0);


    @NotNull(message = "Partner ID is required")
    private Long partnerId;


    @NotEmpty(message = "Upload Files cannot be empty")
    private MultipartFile[] files;


    @NotNull(message = "File Saver implementation cannot be null")
    private FileSaver fileSaver;


    @NotNull(message = "Partner Repository is required")
    private PartnerRepository partnerRepository;


    @NotNull(message = "Partner Image Repository is required")
    private PartnerImageRepository imageRepository;


    private Partner partner;


    @Builder
    private UploadImages(Long partnerId, MultipartFile[] files, FileSaver fileSaver, PartnerRepository partnerRepository, PartnerImageRepository imageRepository) {
        this.partnerId = partnerId;
        this.files = files;
        this.fileSaver = fileSaver;
        this.imageRepository = imageRepository;
        this.partnerRepository = partnerRepository;
    }


    @Override
    protected List<PartnerImage> execute_() throws ServiceException {
        try {
            loadPartner();

            List<PartnerImage> images = new ArrayList<>();
            for (MultipartFile file : files) {
                images.add(saveFile(file));
            }

            return images;
        }
        catch (IOException e) {
            throw new ServiceException(e);
        }
    }


    private void loadPartner() throws EntityNotFoundException {
        partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new EntityNotFoundException("Partner", partnerId));
    }


    private PartnerImage saveFile(MultipartFile file) throws IOException {
        String fullPath = fileSaver.saveFile(getRelativePath(file), file.getInputStream());
        AspectRatio aspectRatio = ImageUtil.computeAspectRatio(file.getInputStream());

        return persistPartnerImage(file.getOriginalFilename(), fullPath, aspectRatio);
    }


    private String getRelativePath(MultipartFile file) {
        return String.format(
            "/%s/img_%d%d_.%s",
            partner.getName().replaceAll(" ", ""),
            System.currentTimeMillis(),
            counter.incrementAndGet() % 999_999,
            FileUtil.getExtension(file.getOriginalFilename())
        );
    }


    private PartnerImage persistPartnerImage(String fileName, String fullPath, AspectRatio aspectRatio) throws IOException {
        PartnerImage image = new PartnerImage();

        image.setPartner(partner);
        image.setSavedPath(fullPath);
        image.setFileName(fileName);
        image.setTimeAdded(ZonedDateTime.now());
        image.setImageKey(UUID.nameUUIDFromBytes(fullPath.getBytes()).toString());
        image.setAspectRatio(aspectRatio);

        return imageRepository.save(image);
    }


}
