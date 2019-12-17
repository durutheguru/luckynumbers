package com.omarze.services.partner;


import com.omarze.api.dto.PartnerDTO;
import com.omarze.entities.Partner;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.PartnerRepository;
import com.omarze.services.partner.handlers.Save;
import com.omarze.services.partner.handlers.GetPartner;
import com.omarze.services.partner.handlers.GetPartners;
import com.omarze.services.partner.handlers.UpdatePartner;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * created by julian
 */
@Service
@RequiredArgsConstructor
public class PartnerService {


    public final PartnerRepository partnerRepository;


    public Partner savePartner(PartnerDTO partnerDto) throws ServiceException {
        return Save.builder()
                .partnerDto(partnerDto)
                .partnerRepository(partnerRepository)
                .build()
                .execute();
    }


    public Partner updatePartner(Partner partner) throws ServiceException {
        return new UpdatePartner(partner, partnerRepository).execute();
    }


    public Page<Partner> getPartners(Integer offset, Integer limit) throws ServiceException {
        return new GetPartners(PageRequest.of(offset, limit, new Sort(Sort.Direction.DESC, "id")), partnerRepository).execute();
    }


    public Partner getPartner(@PathVariable Long id) throws ServiceException {
        return new GetPartner(id, partnerRepository).execute();
    }


}




