package com.omarze.services.partner;


import com.omarze.api.dto.PartnerDTO;
import com.omarze.entities.Partner;
import com.omarze.exception.ServiceException;
import org.springframework.data.domain.Page;

/**
 * created by julian
 */
public interface PartnerService {


    Partner savePartner(PartnerDTO partnerDTO) throws ServiceException;


    Partner updatePartner(PartnerDTO partnerDTO) throws ServiceException;


    Page<Partner> getPartners(Integer page, Integer limit) throws ServiceException;


    Partner getPartner(Long id) throws ServiceException;


}




