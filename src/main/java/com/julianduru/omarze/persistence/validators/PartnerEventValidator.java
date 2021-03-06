package com.julianduru.omarze.persistence.validators;


import com.julianduru.omarze.entities.Partner;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.persistence.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
@RequiredArgsConstructor
public class PartnerEventValidator {


    final PartnerRepository partnerRepository;


    public void validateNewPartner(Partner partner) throws ServiceException {
        validatePartnerName(partner);
    }


    private void validatePartnerName(Partner partner) throws ServiceException {
        boolean existingPartnerName = partnerRepository.existsByName(partner.getName());
        if (existingPartnerName) {
            throw new ServiceException(String.format(
                "Partner name %s already exists",
                partner.getName()
            ));
        }
    }


}
