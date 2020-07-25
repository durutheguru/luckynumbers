package com.omarze.services.partner;


import com.omarze.api.dto.PartnerDTO;
import com.omarze.entities.Partner;
import com.omarze.entities.PartnerImage;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.PartnerImageRepository;
import com.omarze.persistence.PartnerRepository;
import com.omarze.services.FileSaver;
import com.omarze.services.partner.handlers.Save;
import com.omarze.services.partner.handlers.Update;
import com.omarze.services.partner.handlers.UploadImages;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * created by julian
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {


    public final FileSaver fileSaver;


    public final PartnerRepository partnerRepository;


    public final PartnerImageRepository partnerImageRepository;


    @Override
    public Partner savePartner(PartnerDTO partnerDto) throws ServiceException {
        return Save.builder()
                .partnerDTO(partnerDto)
                .partnerRepository(partnerRepository)
                .build()
                .execute();
    }


    @Override
    public Partner updatePartner(PartnerDTO partnerDTO) throws ServiceException {
        return Update.builder()
                .partnerDTO(partnerDTO)
                .partnerRepository(partnerRepository)
                .build()
                .execute();
    }


    @Override
    public Page<Partner> getPartners(Integer page, Integer size) throws ServiceException {
        return partnerRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
    }


    @Override
    public Partner getPartner(@PathVariable Long id) throws ServiceException {
        return partnerRepository.getOne(id);
    }


    @Override
    public List<PartnerImage> uploadImages(Long partnerId, MultipartFile[] files) throws ServiceException {
        return UploadImages.builder()
                .partnerId(partnerId)
                .files(files)
                .fileSaver(fileSaver)
                .partnerRepository(partnerRepository)
                .imageRepository(partnerImageRepository)
                .build()
                .execute();
    }


}

