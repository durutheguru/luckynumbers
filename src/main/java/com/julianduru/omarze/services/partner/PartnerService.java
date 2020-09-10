package com.julianduru.omarze.services.partner;


import com.julianduru.omarze.api.dto.PartnerDTO;
import com.julianduru.omarze.entities.Partner;
import com.julianduru.omarze.entities.PartnerImage;
import com.julianduru.omarze.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * created by julian
 */
public interface PartnerService {


    Partner savePartner(PartnerDTO partnerDTO) throws ServiceException;


    Partner updatePartner(PartnerDTO partnerDTO) throws ServiceException;


    Page<Partner> getPartners(Integer page, Integer limit) throws ServiceException;


    Partner getPartner(Long id) throws ServiceException;


    List<PartnerImage> uploadImages(Long partnerId, MultipartFile[] files) throws ServiceException;


}
